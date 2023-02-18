package com.farmec.project.application.service.secure.signup.sub.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.application.service.secure.signup.sub.SignUpRecordService;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.SignUpResult;

@Service
public class SignUpRecordServiceImpl implements SignUpRecordService {
    private final UserRepository userRepository;
    
    public SignUpRecordServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SignUpResult createSignUp(SignUp signUp) {
       return userRepository.save(signUp);
    }
}
