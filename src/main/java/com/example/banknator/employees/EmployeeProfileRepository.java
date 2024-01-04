package com.example.banknator.employees;

import com.example.banknator.entity.EmployeeProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {
}
