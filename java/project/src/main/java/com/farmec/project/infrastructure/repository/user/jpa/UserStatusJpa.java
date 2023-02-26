package com.farmec.project.infrastructure.repository.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmec.project.infrastructure.entity.user.UserStatus;

public interface UserStatusJpa extends JpaRepository<UserStatus, Long> {
    
    UserStatus findByUserEmail(String email);
}
