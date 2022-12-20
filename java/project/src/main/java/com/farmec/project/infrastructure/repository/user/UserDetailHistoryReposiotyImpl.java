package com.farmec.project.infrastructure.repository.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.farmec.project.application.infrastructure.UserDetailHistoryRepository;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserDetailHistory;
import com.farmec.project.infrastructure.repository.user.jpa.UserDetailHistoryJpaRepository;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpaRepository;

@Repository
public class UserDetailHistoryReposiotyImpl implements UserDetailHistoryRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserDetailHistoryJpaRepository userDetailHistoryJpaRepository;

    @Autowired
    public UserDetailHistoryReposiotyImpl(UserJpaRepository userJpaRepository
        , UserDetailHistoryJpaRepository userDetailHistoryJpaRepository) {
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
