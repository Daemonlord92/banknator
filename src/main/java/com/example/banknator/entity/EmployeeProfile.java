package com.example.banknator.entity;

import com.example.banknator.Enums.BankPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userProfileId;
    private BankPosition position;
    private Long bankId;

    public EmployeeProfile() {}

    public EmployeeProfile(Long userProfileId, BankPosition position, Long bankId) {
        this.userProfileId = userProfileId;
        this.position = position;
        this.bankId = bankId;
    }

    public EmployeeProfile(Long id, Long userProfileId, BankPosition position, Long bankId) {
        this.id = id;
        this.userProfileId = userProfileId;
        this.position = position;
        this.bankId = bankId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public BankPosition getPosition() {
        return position;
    }

    public void setPosition(BankPosition position) {
        this.position = position;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}
