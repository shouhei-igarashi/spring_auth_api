package com.farmec.project.infrastructure.repository.user.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farmec.project.infrastructure.entity.user.User;

public interface UserJpa extends JpaRepository<User, Long> {
    User findByEmail(@Param("email") String email);

    Boolean existsByEmail(@Param("email") String email);

    @Query(value = "select * from user_details where email = :email and role = :role", nativeQuery = true)
     List<User> findByRole(@Param("email") String email, @Param("role") String role);

}
