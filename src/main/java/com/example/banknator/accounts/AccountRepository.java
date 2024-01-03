package com.example.banknator.accounts;

import com.example.banknator.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Long, Account> {
}
