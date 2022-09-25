package com.farmec.project.presentation.payload.security.request;

import javax.validation.constraints.NotBlank;

public class SignIn {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getUsername() {
        return email;
    }

    public void setEMail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
