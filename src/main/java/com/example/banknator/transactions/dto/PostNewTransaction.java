package com.example.banknator.transactions.dto;

import com.example.banknator.Enums.TransactionType;

public record PostNewTransaction(
    long fromId,
    long toId,
    double amount,
    TransactionType transactionType
) {
}
