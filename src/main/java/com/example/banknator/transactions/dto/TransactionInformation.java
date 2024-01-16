package com.example.banknator.transactions.dto;

import com.example.banknator.Enums.TransactionStatus;
import com.example.banknator.Enums.TransactionType;

public record TransactionInformation(
        Long fromId,
        Long toId,
        Double amount,
        TransactionType transactionType,
        TransactionStatus transactionStatus
) {
}
