package com.farmec.project.infrastructure.repository.user;

import org.springframework.stereotype.Repository;

import com.farmec.project.application.infrastructure.UserStatusRepository;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserStatus;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;
import com.farmec.project.infrastructure.repository.user.jpa.UserStatusJpa;

@Repository
public class UserStatusRepositoryImpl implements UserStatusRepository {
    private final UserJpa userJpa;
    private final UserStatusJpa userStatusJpa;
    

    public UserStatusRepositoryImpl(UserJpa userJpa, UserStatusJpa userStatusJpa) {
        this.userJpa = userJpa;
        this.userStatusJpa = userStatusJpa;
    }

    @Override
    public String findStatusByEmail(String email) {
        UserStatus userStatus = userStatusJpa.findByUserEmail(email);
        
        if (userStatus == null) {
            return "";
        }

        return userStatus.getStatus();
    }


    @Override
    public Boolean save(String email, String status) {
        User user = userJpa.findByEmail(email);
        
        if (user == null) {
            return false;
        }

        UserStatus userStatus = new UserStatus(user, status);
        return userStatusJpa.save(userStatus) == null ? false : true;
    }


    @Override
    public Boolean delete(String email) {
        UserStatus userStatus = userStatusJpa.findByUserEmail(email);
        
        if (userStatus == null) {
            return true;
        }

        userStatusJpa.delete(userStatus);
        UserStatus resultUserStatus = userStatusJpa.findByUserEmail(email);
        return resultUserStatus == null ? true : false;
    }
}
