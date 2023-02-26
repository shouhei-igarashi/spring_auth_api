package com.farmec.project.application.service.user.impl.sub.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserStatusRepository;
import com.farmec.project.application.service.user.impl.sub.UserStatusSubQueryService;
import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.UserDetailsImpl;

@Service
public class UserStatusSubQueryServiceImpl implements UserStatusSubQueryService {
    private final UserStatusRepository userStatusRepository;
    
    public UserStatusSubQueryServiceImpl(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }
    
    @Override
    public String getUserStatus(SignIn signIn) {
        return userStatusRepository.findStatusByEmail(signIn.getEmail().toString());        
    }

    @Override
    public String getUserStatus(UserDetailsImpl userDetais) {
        return userStatusRepository.findStatusByEmail(userDetais.getUsername()); 
    }
}
