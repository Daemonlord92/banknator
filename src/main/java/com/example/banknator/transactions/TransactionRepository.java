package com.example.banknator.transactions;

import com.example.banknator.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTopByOrderByIdDesc();
    List<Transaction> findTransactionsByToIdOrFromId(Long toId, Long fromId);
}
