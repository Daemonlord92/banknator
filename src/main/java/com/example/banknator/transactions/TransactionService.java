package com.example.banknator.transactions;

import com.example.banknator.entity.Transaction;
import com.example.banknator.shared.MessageResponse;
import com.example.banknator.transactions.dto.PostNewTransaction;
import com.example.banknator.transactions.dto.TransactionInformation;

import java.util.List;

public interface TransactionService {
    MessageResponse createTransaction(PostNewTransaction request);
    List<TransactionInformation> getTransactionsByAccountId(Long id);
}
