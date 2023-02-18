package com.farmec.project.application.service.secure.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.farmec.project.application.service.secure.SignInQueryService;
import com.farmec.project.domain.model.secure.MyUserDetails;
import com.farmec.project.domain.model.secure.SignIn;

@Service
public class SignInQueryServiceImpl implements SignInQueryService {
    private final AuthenticationManager authenticationManager;

    public SignInQueryServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public MyUserDetails getMyUserDetails(SignIn signIn) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail().toString(), signIn.getPassword().toString()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return MyUserDetails.build(authentication);
    }
}
