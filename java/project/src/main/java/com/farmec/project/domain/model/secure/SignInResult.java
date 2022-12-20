package com.farmec.project.domain.model.secure;

import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.domain.type.secure.auth.Role;

public class SignInResult {
    private final EmailAddress email;
    private final Role role;

    public SignInResult(String email, String role) {
        this.email = new EmailAddress(email);
        this.role = new Role(role);
    }

    public EmailAddress getEmailAddress() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
