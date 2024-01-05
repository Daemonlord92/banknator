package com.example.banknator.entity;

import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.Enums.BankPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HiringApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userCredentialId;
    private Long bankId;
    private BankPosition bankPosition;
    private ApplicationStatus applicationStatus;

    public HiringApplication(Long userCredentialId, BankPosition bankPosition , Long bankId) {
        this.userCredentialId = userCredentialId;
        this.bankId = bankId;
        this.bankPosition = bankPosition;
        this.applicationStatus = ApplicationStatus.PENDING;
    }

    public HiringApplication() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getUserCredentialId() {
        return userCredentialId;
    }

    public void setUserCredentialId(Long userCredentialId) {
        this.userCredentialId = userCredentialId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public BankPosition getBankPosition() {
        return bankPosition;
    }

    public void setBankPosition(BankPosition bankPosition) {
        this.bankPosition = bankPosition;
    }
}
