package com.example.banknator.entity;

import com.example.banknator.Enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private AccountType accountType = AccountType.CHECKING;
    private Double balance = 0.00;
    private Boolean isActive = true;
    private LocalDate createdAt;
    private LocalDate disabledAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserProfile userProfile;

    public Account(UserProfile userProfile, AccountType accountType) {
        this.userProfile = userProfile;
        this.accountType = accountType;
        this.balance = 0.00;
        this.isActive = true;
        this.createdAt = LocalDate.now();
    }

    public Account() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
