package com.farmec.project.application.service.user.impl.sub.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserStatusHistoryRepository;
import com.farmec.project.application.infrastructure.UserStatusRepository;
import com.farmec.project.application.service.user.impl.sub.UserStatusSubRecordService;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.type.secure.auth.Status;

@Service
public class UserStatusSubRecordServiceImpl implements UserStatusSubRecordService {
    private final UserStatusRepository userStatusRepository;
    private final UserStatusHistoryRepository userStatusHistoryRepository;

    public UserStatusSubRecordServiceImpl(UserStatusRepository userStatusRepository
        , UserStatusHistoryRepository userStatusHistoryRepository) {
        this.userStatusRepository = userStatusRepository;
        this.userStatusHistoryRepository = userStatusHistoryRepository;
    }

    @Override
    public Boolean createStatus(SignUp signUp) {
        userStatusRepository.delete(signUp.getEmail().toString());
        Boolean isSavedStatus = userStatusRepository.save(signUp.getEmail().toString(), Status.ACTIVE.getStatus());
        userStatusHistoryRepository.save(signUp.getEmail().toString(), Status.ACTIVE.getStatus());
        
        return isSavedStatus;
    }
    
}
