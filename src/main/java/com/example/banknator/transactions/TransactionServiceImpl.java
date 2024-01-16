package com.example.banknator.transactions;

import com.example.banknator.accounts.AccountService;
import com.example.banknator.accounts.dto.AccountInformation;
import com.example.banknator.entity.Account;
import com.example.banknator.entity.Transaction;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.transactions.dto.PostNewTransaction;
import com.example.banknator.transactions.dto.TransactionInformation;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService{

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
                updateTransaction(finalTransaction);
            }
        });
        return new MessageResponse("Transaction Posted");
    }

    @Override
    public List<TransactionInformation> getTransactionsByAccountId(Long id) {
        return null;
    }

    private void updateTransaction(Transaction transaction) {
        AccountInformation toAccount = accountService.getAccountById(transaction.getToId());
        if(transaction.getFromId() > 0 || transaction.getFromId() != null) {
            AccountInformation fromAccount = accountService.getAccountById(transaction.getFromId());

        }
    }
}
