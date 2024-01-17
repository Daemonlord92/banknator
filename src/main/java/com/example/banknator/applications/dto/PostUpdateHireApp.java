package com.example.banknator.applications.dto;

import com.example.banknator.Enums.BankPosition;

public record PostUpdateHireApp(
        Long id,
        BankPosition bankPosition,
        Double salary
) {
}
