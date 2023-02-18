package com.farmec.project.infrastructure.repository.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.farmec.project.application.infrastructure.UserDetailHistoryRepository;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserDetailHistory;
import com.farmec.project.infrastructure.repository.user.jpa.UserDetailHistoryJpa;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;

@Repository
public class UserDetailHistoryReposiotyImpl implements UserDetailHistoryRepository {
    private final UserJpa userJpaRepository;
    private final UserDetailHistoryJpa userDetailHistoryJpaRepository;

    public UserDetailHistoryReposiotyImpl(UserJpa userJpaRepository
        , UserDetailHistoryJpa userDetailHistoryJpaRepository) {
            this.userJpaRepository = userJpaRepository;
            this.userDetailHistoryJpaRepository = userDetailHistoryJpaRepository;
    }

    @Override
    public Boolean save(Account account) {
        Optional<User> user = userJpaRepository.findByEmail(account.getEmail().toString());

        if (user.isEmpty()) {
            return false;
        }

        UserDetailHistory userDetailHistory = new UserDetailHistory(account, user.get());
        UserDetailHistory resultUserDetailHistory = userDetailHistoryJpaRepository.save(userDetailHistory);

        return !ObjectUtils.isEmpty(resultUserDetailHistory);
    }
}
