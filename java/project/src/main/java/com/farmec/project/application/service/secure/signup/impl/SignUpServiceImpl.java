package com.farmec.project.application.service.secure.signup.impl;

import org.springframework.stereotype.Service;

import com.farmec.project.application.service.secure.signup.SignUpService;
import com.farmec.project.application.service.secure.signup.sub.SignUpQueryService;
import com.farmec.project.application.service.secure.signup.sub.SignUpRecordService;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.SignUpResult;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final SignUpQueryService signUpQueryService;
    private final SignUpRecordService signUpRecordService;
    
    public SignUpServiceImpl(SignUpQueryService signUpQueryService, SignUpRecordService signUpRecordService) {
        this.signUpQueryService = signUpQueryService;
        this.signUpRecordService = signUpRecordService;
    }

    @Override
    public SignUpResult createUser(SignUp signUp, String role) {
        if (signUpQueryService.existsSignUpUser(signUp)) {
            return new SignUpResult(true, "既に登録済みです");
        }


        return signUpRecordService.createSignUp(signUp.build(role));
    }
}
