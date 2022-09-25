package com.farmec.project.application.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmec.project.application.service.security.SignUpService;
import com.farmec.project.domain.model.security.ResultSignUp;
import com.farmec.project.domain.model.security.SignUp;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.repository.security.SecurityUserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {
    private final SecurityUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpServiceImpl(SecurityUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResultSignUp createUser(SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail().toString())) {
            return new ResultSignUp(false, "アカウントは既に存在します");
        }

        User user = new User(signUp.getEmail().toString()
            , passwordEncoder.encode(signUp.getPassword().toString())
            , signUp.getRole().name());

        userRepository.save(user);
        return new ResultSignUp(true, "アカウント登録に成功しました"); 
    }
}
