package com.example.banknator.bank.dto;

import com.example.banknator.Enums.BankStatus;

public record PostUpdateBank(
        Long id,
        BankStatus bankStatus
) {
}
