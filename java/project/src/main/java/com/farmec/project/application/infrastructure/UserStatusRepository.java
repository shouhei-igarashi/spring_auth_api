package com.farmec.project.application.infrastructure;

public interface UserStatusRepository {
    String findStatusByEmail(String email);

    Boolean save(String email, String status);

    Boolean delete(String email);
}
