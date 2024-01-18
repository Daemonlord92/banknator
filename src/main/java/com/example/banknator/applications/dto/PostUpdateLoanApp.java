package com.example.banknator.applications.dto;

import com.example.banknator.Enums.ApplicationStatus;

public record PostUpdateLoanApp(
        Long id,
        ApplicationStatus status
) {
}
