package com.example.banknator.entity;

import com.example.banknator.Enums.AccountType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userProfileId;
    private AccountType accountType = AccountType.CHECKING;
    private Double balance = 0.00;
    private Boolean isActive = true;
    private LocalDate createdAt;
    private LocalDate disabledAt;

    public Account(Long userProfileId, AccountType accountType) {
        this.userProfileId = userProfileId;
        this.accountType = accountType;
        this.balance = 0.00;
        this.isActive = true;
        this.createdAt = LocalDate.now();
    }

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(LocalDate disabledAt) {
        this.disabledAt = disabledAt;
    }
}
