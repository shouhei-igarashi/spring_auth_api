package com.farmec.project.application.service.user.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.farmec.project.application.service.user.UserService;
import com.farmec.project.application.service.user.impl.sub.UserSubQueryService;
import com.farmec.project.application.service.user.impl.sub.UserSubRecordService;
import com.farmec.project.domain.model.secure.UserDetailsImpl;
import com.farmec.project.domain.type.secure.auth.Roles;
import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;

@Service
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final UserSubQueryService userSubQueryService;
    private final UserSubRecordService userSubRecordService;

    public UserServiceImpl(AuthenticationManager authenticationManager
    , UserSubQueryService userSubQueryService, UserSubRecordService userSubRecordService) {
        this.authenticationManager = authenticationManager;
        this.userSubQueryService = userSubQueryService;
        this.userSubRecordService = userSubRecordService;
    }
    
    @Override
    public UserDetailsImpl getUserDetails(SignIn signIn) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail().toString(), signIn.getPassword().toString()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return UserDetailsImpl.build(authentication);
    }

    @Override
    public Boolean createUser(SignUp signUp) {
        if (userSubQueryService.existsUser(signUp)) {
            return false;
        }

        return userSubRecordService.createUser(signUp.build(Roles.ROLE_USER.getRoleName()));
    }

    @Override
    public Boolean createAdminUser(SignUp signUp) {
        if (userSubQueryService.existsUser(signUp)) {
            return false;
        }

        return userSubRecordService.createUser(signUp.build(Roles.ROLE_ADMIN.getRoleName()));
    }

    @Override
    public Boolean isAdminPrivileges(UserDetailsImpl userDetails) {
        return userSubQueryService.existsprivilege(userDetails.build(Roles.ROLE_ADMIN.name()));
    }

    @Override
    public Boolean isUserPrivileges(UserDetailsImpl userDetails) {
        return userSubQueryService.existsprivilege(userDetails.build(Roles.ROLE_USER.name()));
    }
}
