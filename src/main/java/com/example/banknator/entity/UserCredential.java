package com.example.banknator.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class UserCredential implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private Boolean isDisabled;
    private LocalDate createdAt;
    private LocalDate disabledAt;

    @OneToOne(fetch = FetchType.LAZY)
    private UserProfile userProfile;

    public UserCredential(String email, String password) {
        this.email = email;
        this.password = password;
        this.isDisabled = false;
        this.createdAt = LocalDate.now();
    }

    public UserCredential() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDisabled() {
        return isDisabled;
    }

    public void setDisabled(Boolean valid) {
        isDisabled = valid;
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
}
