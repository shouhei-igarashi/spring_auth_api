package com.farmec.project.application.service.security;

import com.farmec.project.domain.model.security.ResultSignUp;
import com.farmec.project.domain.model.security.SignUp;

public interface SignUpService {
    ResultSignUp createUser(SignUp signUp);
}
