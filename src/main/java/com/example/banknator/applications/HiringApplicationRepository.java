package com.example.banknator.applications;

import com.example.banknator.entity.HiringApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HiringApplicationRepository extends JpaRepository<HiringApplication, Long> {
    List<HiringApplication> findAllByUserProfileId(Long id);
}
