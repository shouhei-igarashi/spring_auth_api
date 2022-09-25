package com.farmec.project.application.service.security;

import com.farmec.project.domain.model.security.MyUserDetails;
import com.farmec.project.domain.model.security.SignIn;

public interface SignInService {
    MyUserDetails getMyUserDetails(SignIn signIn);
}
