package com.example.banknator.users.dto;

import java.time.LocalDate;

public record UpdateUserInformation(
    long id,
    String firstName,
    String lastName,
    String email,
    String password,
    String address,
    long phone,
    Integer creditScore,
    LocalDate dateOfBirth
) {
}
