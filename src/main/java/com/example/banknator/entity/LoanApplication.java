package com.example.banknator.entity;

import com.example.banknator.Enums.ApplicationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userCredentialId;
    private Double amount;
    private Double interestRate;
    private ApplicationStatus applicationStatus;

    public LoanApplication() {}

    public LoanApplication(Long userCredentialId, Double amount, Double interestRate) {
        this.userCredentialId = userCredentialId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.applicationStatus = ApplicationStatus.PENDING;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
