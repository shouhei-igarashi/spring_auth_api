package com.farmec.project.domain.model.security;

import com.farmec.project.domain.type.EmailAddress;
import com.farmec.project.domain.type.Password;

public class SignIn {
    private EmailAddress email;
    private Password password;

    public EmailAddress getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
