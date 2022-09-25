package com.farmec.project.domain.model.security;

import com.farmec.project.domain.type.EmailAddress;
import com.farmec.project.domain.type.Password;
import com.farmec.project.domain.type.Role;

public class SignUp {
    private EmailAddress email;
    private Password password;
    private Role role;

    public EmailAddress getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        if (role == null) {
            role = new Role();
        }
        
        return role;
    }
}
