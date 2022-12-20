package com.farmec.project.domain.model.secure;

import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.domain.type.secure.auth.Password;

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
