package com.farmec.project.infrastructure.repository.user.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmec.project.infrastructure.entity.user.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
