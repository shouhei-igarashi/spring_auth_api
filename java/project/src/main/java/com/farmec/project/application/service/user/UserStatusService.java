package com.farmec.project.application.service.user;

import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.UserDetailsImpl;

public interface UserStatusService {
    Boolean createStatus(SignUp signUp);
    Boolean isActiveStatus(SignIn signIn);
    Boolean isActiveStatus(UserDetailsImpl userDetails);
}
