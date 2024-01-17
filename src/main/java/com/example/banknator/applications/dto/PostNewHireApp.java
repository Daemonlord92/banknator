package com.example.banknator.applications.dto;

import com.example.banknator.Enums.BankPosition;

public record PostNewHireApp(
        Long bankId,
        BankPosition position,
        Double requestedSalary,
        Long userProfileId
) {
}
