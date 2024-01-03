package com.example.banknator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userCredentialId;
    private String firstName;
    private String lastName;
    private String address;
    private Long phone;
    private Integer creditScore;
    private LocalDate dateOfBirth;

    public UserProfile(Long userCredentialId,
                       String firstName,
                       String lastName,
                       String address,
                       Long phone,
                       Integer creditScore,
                       LocalDate dateOfBirth) {
        this.userCredentialId = userCredentialId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.creditScore = creditScore;
        this.dateOfBirth = dateOfBirth;
    }

    public UserProfile(Long id,
                       Long userCredentialId,
                       String firstName,
                       String lastName,
                       String address,
                       Long phone,
                       Integer creditScore,
                       LocalDate dateOfBirth) {
        this.id = id;
        this.userCredentialId = userCredentialId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.creditScore = creditScore;
        this.dateOfBirth = dateOfBirth;
    }

    public UserProfile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserCredentialId() {
        return userCredentialId;
    }

    public void setUserCredentialId(Long userCredentialId) {
        this.userCredentialId = userCredentialId;
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
}
