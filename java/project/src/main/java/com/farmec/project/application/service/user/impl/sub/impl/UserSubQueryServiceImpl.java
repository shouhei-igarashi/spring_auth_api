package com.farmec.project.application.service.user.impl.sub.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.application.service.user.impl.sub.UserSubQueryService;
import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.UserDetailsImpl;

@Service
public class UserSubQueryServiceImpl implements UserSubQueryService {
    private final UserRepository userRepository;
    
    public UserSubQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean existsUser (SignUp signUp) {
        return userRepository.existsByEmail(signUp.getEmail().toString());
    }

    @Override
    public Boolean existsprivilege(UserDetailsImpl userDetails) {
        return userRepository.existsByUserDetailsImpl(userDetails);
    }

    @Override
    public UserDetailsImpl getUser(SignIn signIn) {
        return userRepository.findByEmail(signIn.getEmail().toString());
    }

    @Override
    public Boolean existsUser(UserDetailsImpl userDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsUser'");
    }

    
}
