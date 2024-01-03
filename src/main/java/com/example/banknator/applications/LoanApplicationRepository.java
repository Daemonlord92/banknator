package com.example.banknator.applications;

import com.example.banknator.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<Long, LoanApplication> {
}
