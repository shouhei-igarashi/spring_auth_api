package com.farmec.project.infrastructure.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmec.project.infrastructure.entity.user.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    @Query(value = "select * from user_details where email = :email", nativeQuery = true)
    List<UserDetail> findByEmail(@Param("email") String email);
}
