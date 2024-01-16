package com.example.banknator.transactions.exception;

public class InsufficientAccountBalanceException extends ArithmeticException{
    public InsufficientAccountBalanceException(String message) {
        super(message);
    }
}
