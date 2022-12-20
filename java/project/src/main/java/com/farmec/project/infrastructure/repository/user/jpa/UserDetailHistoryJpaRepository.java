package com.farmec.project.infrastructure.repository.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmec.project.infrastructure.entity.user.UserDetailHistory;

public interface UserDetailHistoryJpaRepository extends JpaRepository<UserDetailHistory, Long> {
    
}
