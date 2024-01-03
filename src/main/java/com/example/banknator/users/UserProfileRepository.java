package com.example.banknator.users;

import com.example.banknator.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<Long, UserProfile> {
}
