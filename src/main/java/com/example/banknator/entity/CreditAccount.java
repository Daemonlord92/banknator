package com.example.banknator.entity;

import com.example.banknator.Enums.AccountType;
import jakarta.persistence.Entity;

@Entity
public class CreditAccount extends Account{
    private Double minPayment;
    private Double interestRate;

    public CreditAccount(Long userCredentialId, AccountType accountType, Double balance, Boolean isActive, Double minPayment, Double interestRate) {
        super(userCredentialId, accountType, balance, isActive);
        this.minPayment = minPayment = 0.00;
        this.interestRate = interestRate = 0.00;
    }

    public CreditAccount() {

    }

    public Double getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(Double minPayment) {
        this.minPayment = minPayment;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }
}
