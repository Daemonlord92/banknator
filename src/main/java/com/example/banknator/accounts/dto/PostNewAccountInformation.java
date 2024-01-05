package com.example.banknator.accounts.dto;

import com.example.banknator.Enums.AccountType;

public record PostNewAccountInformation(
        long userProfileId,
        AccountType accountType,
        double balance
) {
}
