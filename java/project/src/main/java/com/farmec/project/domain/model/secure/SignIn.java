package com.farmec.project.domain.model.secure;

import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.domain.type.secure.auth.Password;
import com.farmec.project.domain.type.secure.auth.Role;

/**
 * SignInモデル
 */
public class SignIn {
    // emailアドレス
    private EmailAddress email;
    
    // パスワード
    private Password password;

    // ロール
    private Role role;

    public SignIn() { }

    public SignIn(EmailAddress email, Password password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public EmailAddress getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public SignIn build (String role) throws CloneNotSupportedException {
        return new SignIn(email, password, new Role(role));
    }
}
