package com.example.banknator.auth.dto;

public record AuthRequest(
        String email,
        String password
) {
}
