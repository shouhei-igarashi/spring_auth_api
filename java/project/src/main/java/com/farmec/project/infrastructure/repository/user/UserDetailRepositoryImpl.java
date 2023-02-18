package com.farmec.project.infrastructure.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.farmec.project.application.infrastructure.UserDetailRepository;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserDetail;
import com.farmec.project.infrastructure.repository.user.jpa.UserDetailJpa;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;

@Repository
public class UserDetailRepositoryImpl implements UserDetailRepository {
    private final UserJpa userJpaRepository;
    private final UserDetailJpa userDetailJpaRepository;

    public UserDetailRepositoryImpl(UserJpa userJpaRepository, UserDetailJpa userDetailJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.userDetailJpaRepository = userDetailJpaRepository;
    }

    @Override
    public Boolean save(Account account) {
        Optional<User> user = userJpaRepository.findByEmail(account.getEmail().toString());
        
        if (user.isEmpty()) {
            return false;
        }

        UserDetail userDetail = new UserDetail(account, user.get());
        UserDetail resultUserDetail = userDetailJpaRepository.save(userDetail);

        return !ObjectUtils.isEmpty(resultUserDetail);
    }

    @Override
    public void delete(Account account) {
        List<UserDetail> userDetails = userDetailJpaRepository.findByEmail(account.getEmail().toString());

        if (userDetails.isEmpty()) {
            return;
        }

        userDetailJpaRepository.delete(userDetails.get(0));
    }
}
