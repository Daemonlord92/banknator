package com.example.banknator.entity;

import com.example.banknator.Enums.BankPosition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class EmployeeProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BankPosition position;
    private Long bankId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserProfile userProfile;

    public EmployeeProfile() {}

    public EmployeeProfile(BankPosition position, Long bankId, UserProfile userProfile) {
        this.position = position;
        this.bankId = bankId;
        this.userProfile = userProfile;
    }

    public EmployeeProfile(Long id, BankPosition position, Long bankId, UserProfile userProfile) {
        this.id = id;
        this.position = position;
        this.bankId = bankId;
        this.userProfile = userProfile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
