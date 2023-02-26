package com.farmec.project.infrastructure.repository.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farmec.project.infrastructure.entity.user.UserDetail;

public interface UserDetailJpa extends JpaRepository<UserDetail, Long> {
    @Query(value = "select * from user_details where email = :email", nativeQuery = true)
    UserDetail findByEmail(@Param("email") String email);
}
