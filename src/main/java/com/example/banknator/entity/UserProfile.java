package com.example.banknator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Long phone;
    @Column(nullable = false)
    private Integer creditScore;
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private UserCredential userCredential;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToOne(fetch = FetchType.LAZY)
    private EmployeeProfile employeeProfile;

    @OneToMany(fetch = FetchType.LAZY)
    private List<LoanApplication> loanApplication;

    @OneToMany(fetch = FetchType.LAZY)
    private List<HiringApplication> hiringApplications;

    public UserProfile(
                       String firstName,
                       String lastName,
                       String address,
                       Long phone,
                       Integer creditScore,
                       LocalDate dateOfBirth,
                       UserCredential userCredential) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.creditScore = creditScore;
        this.dateOfBirth = dateOfBirth;
        this.userCredential = userCredential;
    }

    public UserProfile(Long id,
                       String firstName,
                       String lastName,
                       String address,
                       Long phone,
                       Integer creditScore,
                       LocalDate dateOfBirth,
                       UserCredential userCredential) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.creditScore = creditScore;
        this.dateOfBirth = dateOfBirth;
        this.userCredential = userCredential;
    }

    public UserProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public EmployeeProfile getEmployeeProfile() {
        return employeeProfile;
    }

    public void setEmployeeProfile(EmployeeProfile employeeProfile) {
        this.employeeProfile = employeeProfile;
    }

    public List<LoanApplication> getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(List<LoanApplication> loanApplication) {
        this.loanApplication = loanApplication;
    }

    public List<HiringApplication> getHiringApplications() {
        return hiringApplications;
    }

    public void setHiringApplications(List<HiringApplication> hiringApplications) {
        this.hiringApplications = hiringApplications;
    }
}
