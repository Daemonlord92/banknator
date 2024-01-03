package com.example.banknator.bank;

import com.example.banknator.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Long, Bank> {
}
