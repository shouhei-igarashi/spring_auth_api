package com.farmec.project.application.service.account.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserDetailHistoryRepository;
import com.farmec.project.application.infrastructure.UserDetailRepository;
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
    private final UserDetailRepository userDetailRepository;
    private final UserDetailHistoryRepository userDetailHistoryRepository;

    @Autowired
    public AccountRecordServiceImpl(UserDetailRepository userDetailRepository
        , UserDetailHistoryRepository userDetailHistoryRepository) {
        this.userDetailRepository = userDetailRepository;
        this.userDetailHistoryRepository = userDetailHistoryRepository;
    }
    
    @Override
    public Boolean createAccount(Account account) {
        userDetailRepository.delete(account);
        
        if (!userDetailRepository.save(account)) {
            return false;
        }
        
        if (!userDetailHistoryRepository.save(account)) {
            return false;
        }

        return true;
    }
}
