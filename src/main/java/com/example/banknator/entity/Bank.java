package com.example.banknator.entity;

import com.example.banknator.Enums.BankStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name = "Horrorbank";
    private Double balance = 500000.00;
    private BankStatus bankStatus = BankStatus.UNDER_CONSTRUCTION;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EmployeeProfile> employeeProfiles;

    public Bank(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Bank() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public BankStatus getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(BankStatus bankStatus) {
        this.bankStatus = bankStatus;
    }

    public List<EmployeeProfile> getEmployeeProfiles() {
        return employeeProfiles;
    }

    public void setEmployeeProfiles(List<EmployeeProfile> employeeProfiles) {
        this.employeeProfiles = employeeProfiles;
    }
}
