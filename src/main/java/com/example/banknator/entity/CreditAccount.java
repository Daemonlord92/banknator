package com.example.banknator.entity;

import com.example.banknator.Enums.AccountType;
import jakarta.persistence.Entity;

@Entity
public class CreditAccount extends Account{
    private Double creditLimit;
    private Double minPayment;
    private Double interestRate;

    public CreditAccount(UserProfile userProfile, AccountType accountType) {
        super(userProfile, accountType);
        this.creditLimit = 1500.00;
        this.minPayment = minPayment = 0.00;
        this.interestRate = interestRate = 0.09;
    }

    public CreditAccount() {

    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
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
