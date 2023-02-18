package com.farmec.project.application.service.secure.signup;

import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.SignUpResult;

public interface SignUpService {
    SignUpResult createUser(SignUp signUp, String role);
}
