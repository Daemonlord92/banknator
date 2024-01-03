package com.example.banknator.users;

import com.example.banknator.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<Long, UserCredential> {
}
