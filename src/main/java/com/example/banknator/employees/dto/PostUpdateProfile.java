package com.example.banknator.employees.dto;

import com.example.banknator.Enums.BankPosition;

public record PostUpdateProfile(
        Long id,
        BankPosition position,
        Double salary,
        Long userProfileId
) {
}
