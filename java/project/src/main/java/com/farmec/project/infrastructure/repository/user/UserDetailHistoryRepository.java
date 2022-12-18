package com.farmec.project.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmec.project.infrastructure.entity.user.UserDetailHistory;

@Repository
public interface UserDetailHistoryRepository extends JpaRepository<UserDetailHistory, Long> {
    
}
