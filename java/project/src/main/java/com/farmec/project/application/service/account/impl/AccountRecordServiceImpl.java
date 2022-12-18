package com.farmec.project.application.service.account.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmec.project.application.service.account.AccountRecordService;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.entity.user.UserDetail;
import com.farmec.project.infrastructure.entity.user.UserDetailHistory;
import com.farmec.project.infrastructure.repository.user.UserDetailHistoryRepository;
import com.farmec.project.infrastructure.repository.user.UserDetailRepository;
import com.farmec.project.infrastructure.repository.user.UserRepository;

@Service
public class AccountRecordServiceImpl implements AccountRecordService {
    private final UserRepository userRepository;
    private final UserDetailRepository userDetailRepository;
    private final UserDetailHistoryRepository userDetailHistoryRepository;

    @Autowired
    public AccountRecordServiceImpl(UserRepository userRepository, UserDetailRepository userDetailRepository
    , UserDetailHistoryRepository userDetailHistoryRepository) {
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
