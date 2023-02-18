package com.farmec.project.application.service.account.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserDetailHistoryRepository;
import com.farmec.project.application.infrastructure.UserDetailRepository;
import com.farmec.project.application.service.account.AccountRecordService;
import com.farmec.project.domain.model.account.Account;

@Service
public class AccountRecordServiceImpl implements AccountRecordService {
    private final UserDetailRepository userDetailRepository;
    private final UserDetailHistoryRepository userDetailHistoryRepository;

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
