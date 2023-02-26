package com.farmec.project.application.infrastructure;

public interface UserStatusHistoryRepository {
    Boolean save(String email, String status);
}
