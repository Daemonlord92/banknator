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
    private Double salary;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserProfile userProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Bank bank;

    public EmployeeProfile() {}

    public EmployeeProfile(BankPosition position, Double salary, UserProfile userProfile, Bank bank) {
        this.position = position;
        this.salary = salary;
        this.userProfile = userProfile;
        this.bank = bank;
    }

    public EmployeeProfile(Long id, BankPosition position, Double salary, UserProfile userProfile, Bank bank) {
        this.id = id;
        this.position = position;
        this.salary = salary;
        this.userProfile = userProfile;
        this.bank = bank;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
