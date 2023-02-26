package com.farmec.project.infrastructure.repository.user;

import org.springframework.stereotype.Repository;

import com.farmec.project.application.infrastructure.UserStatusHistoryRepository;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserStatusHistory;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;
import com.farmec.project.infrastructure.repository.user.jpa.UserStatusHistoryJpa;

@Repository
public class UserStatusHistoryRepositoryImpl implements UserStatusHistoryRepository {
    private final UserJpa userJpa;
    private final UserStatusHistoryJpa userStatusHistoryJpa;

    public UserStatusHistoryRepositoryImpl(UserJpa userJpa, UserStatusHistoryJpa userStatusHistoryJpa) {
        this.userJpa = userJpa;
        this.userStatusHistoryJpa = userStatusHistoryJpa;
    }

    @Override
    public Boolean save(String email, String status) {
        User user = userJpa.findByEmail(email);
        
        if (user == null) {
            return false;
        }

        UserStatusHistory userStatusHistory = new UserStatusHistory(user, status);
        return userStatusHistoryJpa.save(userStatusHistory) == null ? false : true;
    }
}
