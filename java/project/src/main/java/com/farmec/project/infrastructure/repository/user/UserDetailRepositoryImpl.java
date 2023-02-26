package com.farmec.project.infrastructure.repository.user;

import org.springframework.stereotype.Repository;

import com.farmec.project.application.infrastructure.UserDetailRepository;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserDetail;
import com.farmec.project.infrastructure.repository.user.jpa.UserDetailJpa;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;

@Repository
public class UserDetailRepositoryImpl implements UserDetailRepository {
    private final UserJpa userJpa;
    private final UserDetailJpa userDetailJpa;

    public UserDetailRepositoryImpl(UserJpa userJpa, UserDetailJpa userDetailJpa) {
        this.userJpa = userJpa;
        this.userDetailJpa = userDetailJpa;
    }

    @Override
    public Boolean save(Account account) {
        User user = userJpa.findByEmail(account.getEmail().toString());
        
        if (user == null) {
            return false;
        }

        UserDetail userDetail = new UserDetail(account, user);
        return userDetailJpa.save(userDetail) == null ? false : true;
    }

    @Override
    public Boolean delete(Account account) {
        UserDetail userDetail = userDetailJpa.findByEmail(account.getEmail().toString());

        if (userDetail == null) {
            return true;
        }

        userDetailJpa.delete(userDetail);
        UserDetail deletedUserDetail = userDetailJpa.findByEmail(account.getEmail().toString());
        
        return deletedUserDetail == null ? true : false;
    }

    @Override
    public Account findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public Boolean existsByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByEmail'");
    }
}
