package com.example.banknator.accounts;

import com.example.banknator.Enums.AccountType;
import com.example.banknator.Enums.TransactionType;
import com.example.banknator.accounts.dto.AccountInformation;
import com.example.banknator.accounts.dto.PostNewAccountInformation;
import com.example.banknator.accounts.exception.ResourceDisabledException;
import com.example.banknator.entity.Account;
import com.example.banknator.entity.CreditAccount;
import com.example.banknator.entity.Transaction;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.users.UserProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    protected final AccountRepository accountRepository;
    protected final UserProfileRepository userProfileRepository;

    public AccountServiceImpl(AccountRepository accountRepository, UserProfileRepository userProfileRepository) {
        this.accountRepository = accountRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public MessageResponse createAccount(PostNewAccountInformation request) {
        if(request.accountType() == AccountType.CREDIT) {
            CreditAccount creditAccount = new CreditAccount();
            creditAccount.setAccountType(request.accountType());
            creditAccount.setUserProfile(userProfileRepository.findById(request.userProfileId()).orElseThrow());
            creditAccount.setBalance(request.balance());
            creditAccount.setCreatedAt(LocalDate.now());
            creditAccount.setCreditLimit(1500.00);
            accountRepository.save(creditAccount);
            return new MessageResponse("Credit Account Created");
        } else if (request.accountType() == AccountType.LOAN) {
            CreditAccount creditAccount = new CreditAccount();
            creditAccount.setAccountType(request.accountType());
            creditAccount.setUserProfile(userProfileRepository.findById(request.userProfileId()).orElseThrow());
            creditAccount.setBalance(request.balance());
            creditAccount.setMinPayment(request.balance() / 24);
            creditAccount.setInterestRate(2.5);
            creditAccount.setCreatedAt(LocalDate.now());
            accountRepository.save(creditAccount);
            return new MessageResponse("Loan Account Created");
        } else {
            Account account = new Account(userProfileRepository.findById(request.userProfileId()).orElseThrow(), request.accountType());
            accountRepository.save(account);
            return new MessageResponse("Account Created");
        }

    }

    @Override
    public List<AccountInformation> getAllAccountsByUserProfileId(long id) {
        List<Account> accounts = accountRepository.findAll();
        List<AccountInformation> accountInformations = new ArrayList<>();
        for (Account account : accounts) {
            if(account.getUserProfile().getId() == id) {
                if(account.getAccountType() == AccountType.CREDIT || account.getAccountType() == AccountType.LOAN && account.getActive()) {
                    CreditAccount account1 = (CreditAccount) account;
                    accountInformations.add(
                            new AccountInformation(
                                    account.getId(),
                                    account.getAccountType(),
                                    account.getBalance(),
                                    account1.getMinPayment(),
                                    account1.getInterestRate()
                            ));
                }
                else if (account.getActive()) {
                    accountInformations.add(
                            new AccountInformation(
                                    account.getId(),
                                    account.getAccountType(),
                                    account.getBalance(),
                                    0.0,
                                    0.0
                            )
                    );
                }
            }
        }
        return accountInformations;
    }

    @Override
    public AccountInformation getAccountById(long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()) throw new EntityNotFoundException("Account not found");
        if(account.get().getAccountType() == AccountType.CREDIT || account.get().getAccountType() == AccountType.LOAN && account.get().getActive()) {
            CreditAccount creditAccount = (CreditAccount) account.get();
            return new AccountInformation(creditAccount.getId(),
                    creditAccount.getAccountType(),
                    creditAccount.getBalance(),
                    creditAccount.getMinPayment(),
                    creditAccount.getInterestRate());
        } else if (account.get().getActive()) {
            AccountInformation accountInformation = new AccountInformation(
                    account.get().getId(),
                    account.get().getAccountType(),
                    account.get().getBalance(),
                    0.0,
                    0.0
            );
            return accountInformation;
        } else {
            throw new ResourceDisabledException("Account with id " + id + " is disabled");
        }
    }

    @Override
    public MessageResponse updateBalance(Transaction transaction) {
        Optional<Account> fromAccount = accountRepository.findById(transaction.getFromId());
        Optional<Account> toAccount = accountRepository.findById(transaction.getToId());
        if(fromAccount.isPresent() &&
                toAccount.get().getActive() && fromAccount.get().getActive()) {
            fromAccount.get().setBalance(fromAccount.get().getBalance() - transaction.getAmount());
            toAccount.get().setBalance(toAccount.get().getBalance() + transaction.getAmount());
            accountRepository.saveAll(Arrays.asList(fromAccount.get(), toAccount.get()));
        }
        if(transaction.getTransactionType() == TransactionType.DEPOSIT ||
                transaction.getTransactionType() == TransactionType.CHARGE &&
                        toAccount.get().getActive()
        ) {
            toAccount.get().setBalance(toAccount.get().getBalance() + transaction.getAmount());
            accountRepository.save(toAccount.get());
        }
        if(transaction.getTransactionType() == TransactionType.MAKE_PAYMENT &&
                toAccount.get().getActive()) {
            toAccount.get().setBalance(toAccount.get().getBalance() - transaction.getAmount());
            accountRepository.save(toAccount.get());
        }
        return new MessageResponse("Account Updated");
    }

    @Override
    public MessageResponse disableAccount(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()) throw new EntityNotFoundException("Account not found");
        if(!account.get().getActive()) throw new RuntimeException("Account is already disabled");
        account.get().setActive(false);
        account.get().setDisabledAt(LocalDate.now());
        accountRepository.save(account.get());
        return new MessageResponse("Account Disabled");
    }

    @Override
    public MessageResponse enableAccount(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isEmpty()) throw new EntityNotFoundException("Account not found");
        if(account.get().getActive()) throw new RuntimeException("Account is already active");
        account.get().setActive(true);
        account.get().setDisabledAt(null);
        accountRepository.save(account.get());
        return new MessageResponse("Account Enabled");
    }
}
