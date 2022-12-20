package com.farmec.project.application.service.account.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmec.project.application.service.account.AccountRecordService;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserDetail;
import com.farmec.project.infrastructure.entity.user.UserDetailHistory;
import com.farmec.project.infrastructure.repository.user.jpa.UserDetailHistoryJpaRepository;
import com.farmec.project.infrastructure.repository.user.jpa.UserDetailJpaRepository;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpaRepository;

@Service
public class AccountRecordServiceImpl implements AccountRecordService {
    private final UserJpaRepository userRepository;
    private final UserDetailJpaRepository userDetailRepository;
    private final UserDetailHistoryJpaRepository userDetailHistoryRepository;

    @Autowired
    public AccountRecordServiceImpl(UserJpaRepository userRepository, UserDetailJpaRepository userDetailRepository
    , UserDetailHistoryJpaRepository userDetailHistoryRepository) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.userDetailHistoryRepository = userDetailHistoryRepository;
    }
    
    @Override
    public void registAccount(Account account) {
        Optional<User> user = userRepository.findByEmail(account.getEmail().toString());
        UserDetail userDetail = new UserDetail(account, user.get());
        List<UserDetail> recordUserDetails = userDetailRepository.findByEmail(account.getEmail().toString());

        if (!recordUserDetails.isEmpty()) {
            userDetailRepository.delete(recordUserDetails.get(0));
        }
        
        userDetailRepository.save(userDetail);
        
        UserDetailHistory userDetailHistory = new UserDetailHistory(account, user.get());
        userDetailHistoryRepository.save(userDetailHistory);
    }
}
