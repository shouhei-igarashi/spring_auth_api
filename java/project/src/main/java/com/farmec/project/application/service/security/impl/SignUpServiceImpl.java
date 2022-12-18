package com.farmec.project.application.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmec.project.application.service.security.SignUpService;
import com.farmec.project.domain.model.security.ResultSignUp;
import com.farmec.project.domain.model.security.SignUp;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.repository.user.UserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    
    @Autowired
    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResultSignUp createUser(SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail().toString())) {
            return new ResultSignUp(false, "アカウントは既に存在します");
        }

        User user = new User(signUp);

        if (userRepository.save(user) != null) {
            return new ResultSignUp(true, "アカウント登録に成功しました"); 
        }
        
        return new ResultSignUp(false, "アカウントの登録に失敗しました。");
    }
}
