package com.example.banknator.transactions;

import com.example.banknator.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTopByOrderByIdDesc();
}
