package com.farmec.project.infrastructure.repository.user;

import org.springframework.stereotype.Repository;

import com.farmec.project.application.infrastructure.UserDetailHistoryRepository;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserDetailHistory;
import com.farmec.project.infrastructure.repository.user.jpa.UserDetailHistoryJpa;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;

@Repository
public class UserDetailHistoryReposiotyImpl implements UserDetailHistoryRepository {
    private final UserJpa userJpa;
    private final UserDetailHistoryJpa userDetailHistoryJpa;

    public UserDetailHistoryReposiotyImpl(UserJpa userJpa, UserDetailHistoryJpa userDetailHistoryJpa) {
        this.userJpa = userJpa;
        this.userDetailHistoryJpa = userDetailHistoryJpa;
    }

    @Override
    public Boolean save(Account account) {
        User user = userJpa.findByEmail(account.getEmail().toString());

        if (user == null) {
            return false;
        }

        UserDetailHistory userDetailHistory = new UserDetailHistory(account, user);
        return userDetailHistoryJpa.save(userDetailHistory) == null ? false : true;
    }
}
