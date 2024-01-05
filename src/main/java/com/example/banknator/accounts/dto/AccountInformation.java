package com.example.banknator.accounts.dto;

import com.example.banknator.Enums.AccountType;

public record AccountInformation(
        Long id,
        AccountType accountType,
        Double balance,
        Double minPay,
        Double interestRate
) {

}
