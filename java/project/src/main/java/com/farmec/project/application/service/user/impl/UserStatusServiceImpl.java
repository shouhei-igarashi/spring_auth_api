package com.farmec.project.application.service.user.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.service.user.UserStatusService;
import com.farmec.project.application.service.user.impl.sub.UserStatusSubQueryService;
import com.farmec.project.application.service.user.impl.sub.UserStatusSubRecordService;
import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.UserDetailsImpl;
import com.farmec.project.domain.type.secure.auth.Status;

@Service
public class UserStatusServiceImpl implements UserStatusService {
    private final UserStatusSubQueryService userStatusSubQueryService;
    private final UserStatusSubRecordService userStatusSubRecordService;

    public UserStatusServiceImpl(UserStatusSubQueryService userStatusSubQueryService
        , UserStatusSubRecordService userStatusSubRecordService) {
            this.userStatusSubQueryService = userStatusSubQueryService;
            this.userStatusSubRecordService = userStatusSubRecordService;
        }

    @Override
    public Boolean createStatus(SignUp signUp) {
        return userStatusSubRecordService.createStatus(signUp);
    }

    @Override
    public Boolean isActiveStatus(SignIn signIn) {
        String status = userStatusSubQueryService.getUserStatus(signIn);
        return Status.ACTIVE.name().equals(status) ? true : false;
    }

    @Override
    public Boolean isActiveStatus(UserDetailsImpl userDetails) {
        String status = userStatusSubQueryService.getUserStatus(userDetails);
        return Status.ACTIVE.name().equals(status) ? true : false;
    }
    
}
