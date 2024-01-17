package com.example.banknator.entity;

import com.example.banknator.Enums.ApplicationStatus;
import com.example.banknator.Enums.BankPosition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class HiringApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long bankId;
    private BankPosition bankPosition;
    private Double requestedSalary;
    private ApplicationStatus applicationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserProfile userProfile;

    public HiringApplication(BankPosition bankPosition, Double requestedSalary, Long bankId, UserProfile userProfile) {
        this.bankId = bankId;
        this.bankPosition = bankPosition;
        this.requestedSalary = requestedSalary;
        this.applicationStatus = ApplicationStatus.PENDING;
        this.userProfile = userProfile;
    }

    public HiringApplication() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Double getRequestedSalary() {
        return requestedSalary;
    }

    public void setRequestedSalary(Double requestedSalary) {
        this.requestedSalary = requestedSalary;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
