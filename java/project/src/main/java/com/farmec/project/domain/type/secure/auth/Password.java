package com.farmec.project.domain.type.secure.auth;

import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {
    
    @NotBlank
    @Size(min = 6, max = 40)
    String value;

    public Password(String password) {
        value = password;
    }

    public String getEncodePassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
