package com.farmec.project.application.service.secure.signup.sub;

import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.SignUpResult;

public interface SignUpRecordService {
    SignUpResult createSignUp(SignUp signUp);
}
