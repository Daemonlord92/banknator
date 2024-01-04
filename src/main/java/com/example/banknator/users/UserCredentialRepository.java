package com.example.banknator.users;

import com.example.banknator.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Long> {
    Optional<UserCredential> findByEmail(String email);
}
