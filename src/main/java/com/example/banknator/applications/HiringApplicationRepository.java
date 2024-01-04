package com.example.banknator.applications;

import com.example.banknator.entity.HiringApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiringApplicationRepository extends JpaRepository<HiringApplication, Long> {
}
