package com.farmec.project.domain.model.secure;

import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.domain.type.secure.auth.Role;

public class SignUpResult {
    private String message;
    private Boolean isSuccess;

    private EmailAddress email;
    private Role role;

    public SignUpResult() {
        email = new EmailAddress();
        role = new Role();

        isSuccess = false;
        message = "登録失敗";
    }

    public SignUpResult(Boolean isSuccess, String message) {
        email = new EmailAddress();
        role = new Role();

        this.isSuccess = isSuccess;
        this.message = message;
    }

    public SignUpResult(String email, String role) {
        this.email = new EmailAddress(email);
        this.role = new Role(role);
        
        isSuccess = true;
        message = "登録成功";
        
    }

    public EmailAddress getEmailAddress() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

}
