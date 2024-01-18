package com.example.banknator.applications.dto;

public record PostNewLoanApp(
        Double amount,
        Double interestRate,
        Long userProfileId
) {
}
