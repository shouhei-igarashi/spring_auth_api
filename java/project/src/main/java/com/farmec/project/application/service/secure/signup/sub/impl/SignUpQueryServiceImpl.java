package com.farmec.project.application.service.secure.signup.sub.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.application.service.secure.signup.sub.SignUpQueryService;
import com.farmec.project.domain.model.secure.SignUp;

@Service
public class SignUpQueryServiceImpl implements SignUpQueryService {
    private final UserRepository userRepository;
    
    public SignUpQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existsSignUpUser (SignUp signUp) {
        return userRepository.existsByEmail(signUp);
    }
}
