package com.farmec.project.domain.model.secure;

import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.domain.type.secure.auth.Password;
import com.farmec.project.domain.type.secure.auth.Role;

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
