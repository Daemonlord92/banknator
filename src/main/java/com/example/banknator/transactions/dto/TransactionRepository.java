package com.example.banknator.transactions.dto;

import com.example.banknator.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Long, Transaction> {
}
