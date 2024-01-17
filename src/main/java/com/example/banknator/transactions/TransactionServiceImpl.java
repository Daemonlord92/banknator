package com.example.banknator.transactions;

import com.example.banknator.Enums.TransactionStatus;
import com.example.banknator.Enums.TransactionType;
import com.example.banknator.accounts.AccountService;
import com.example.banknator.accounts.dto.AccountInformation;
import com.example.banknator.entity.Account;
import com.example.banknator.entity.Transaction;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.transactions.dto.PostNewTransaction;
import com.example.banknator.transactions.dto.TransactionInformation;
import com.example.banknator.transactions.exception.InsufficientAccountBalanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
    protected final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    protected final TransactionRepository transactionRepository;
    protected final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public MessageResponse createTransaction(PostNewTransaction request) {
        Transaction transaction = new Transaction(
                request.fromId(),
                request.toId(),
                request.transactionType(),
                request.amount());
        transactionRepository.save(transaction);
        Transaction finalTransaction = transactionRepository.findTopByOrderByIdDesc();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(finalTransaction.getId() > 0)
            {
                logger.info("TransactionServiceImpl:MJM:L47-> UPDATING TRANSACTION " + finalTransaction.getId());
                updateTransaction(finalTransaction);
            }
        });
        thread.setDaemon(true);
        thread.start();
        return new MessageResponse("Transaction Posted");
    }

    @Override
    public List<TransactionInformation> getTransactionsByAccountId(Long id) {
        List<Transaction> transactions = transactionRepository.findTransactionsByToIdOrFromId(id, id);
        List<TransactionInformation> transactionInformations = new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionInformation transactionInformation = new TransactionInformation(
                    transaction.getFromId(),
                    transaction.getToId(),
                    transaction.getAmount(),
                    transaction.getTransactionType(),
                    transaction.getTransactionStatus()
            );
            transactionInformations.add(transactionInformation);
        }
        return transactionInformations;
    }

    private void updateTransaction(Transaction transaction) {
        AccountInformation toAccount = accountService.getAccountById(transaction.getToId());
        if(transaction.getFromId() > 0 && transaction.getTransactionType() == TransactionType.WITHDRAW){
            AccountInformation fromAccount = accountService.getAccountById(transaction.getFromId());
            if(fromAccount.balance() >= transaction.getAmount()){
                transaction.setTransactionStatus(TransactionStatus.APPROVED);
                updateTransaction(transaction);
                transactionRepository.save(transaction);
                return;
            } else {
                transaction.setTransactionStatus(TransactionStatus.DECLINED);
                transactionRepository.save(transaction);
                throw new InsufficientAccountBalanceException("Account " + transaction.getFromId()+ " has insufficient balance");
            }
        }
        if(toAccount.balance() <= transaction.getAmount() && transaction.getTransactionType() == TransactionType.MAKE_PAYMENT) {
            transaction.setAmount(toAccount.balance());
            transaction.setTransactionStatus(TransactionStatus.APPROVED);
            accountService.updateBalance(transaction);
            accountService.disableAccount(toAccount.id());
            transactionRepository.save(transaction);
            return;
        }
        transaction.setTransactionStatus(TransactionStatus.APPROVED);
        accountService.updateBalance(transaction);
        transactionRepository.save(transaction);
    }
}
