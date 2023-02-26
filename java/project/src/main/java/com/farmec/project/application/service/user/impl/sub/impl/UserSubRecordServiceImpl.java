package com.farmec.project.application.service.user.impl.sub.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.application.service.user.impl.sub.UserSubRecordService;
import com.farmec.project.domain.model.secure.SignUp;

@Service
public class UserSubRecordServiceImpl implements UserSubRecordService {
    private final UserRepository userRepository;
    
    public UserSubRecordServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean createUser(SignUp signUp) {
       return userRepository.save(signUp);
    }
}
