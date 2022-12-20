package com.farmec.project.application.service.secure.signup.sub;

import com.farmec.project.domain.model.secure.SignUp;

public interface SignUpQueryService {
    Boolean existsSignUpUser(SignUp signUp);
}
