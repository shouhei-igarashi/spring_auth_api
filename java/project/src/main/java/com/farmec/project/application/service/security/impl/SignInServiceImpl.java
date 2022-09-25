package com.farmec.project.application.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.farmec.project.application.service.security.SignInService;
import com.farmec.project.domain.model.security.MyUserDetails;
import com.farmec.project.domain.model.security.SignIn;
import com.farmec.project.presentation.config.jwt.JwtUtils;

@Service
public class SignInServiceImpl implements SignInService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public SignInServiceImpl(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public MyUserDetails getMyUserDetails(SignIn signIn) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail().toString(), signIn.getPassword().toString()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        userDetails.setJwtToken(jwt);

        return userDetails;
    }
}
