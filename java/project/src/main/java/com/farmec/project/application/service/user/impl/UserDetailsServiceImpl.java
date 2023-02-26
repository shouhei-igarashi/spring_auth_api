package com.farmec.project.application.service.user.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.domain.model.secure.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetailsImpl userDetails = userRepository.findByEmail(email);
        userDetails.checkUser();

        return userDetails;
    }
}
