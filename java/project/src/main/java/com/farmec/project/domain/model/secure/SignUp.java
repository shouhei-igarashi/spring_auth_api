package com.farmec.project.domain.model.secure;

import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.domain.type.secure.auth.Password;
import com.farmec.project.domain.type.secure.auth.Role;

/**
 * SignUpモデル
 */
public class SignUp {
    // emailアドレス
    private EmailAddress email;
    
    // パスワード
    private Password password;
    
    // ロール
    private Role role;

    public SignUp() {}

    public SignUp(EmailAddress email, Password password, Role role) {
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
        if (role == null) {
            role = new Role();
        }
        
        return role;
    }

    public SignUp build(String roleStr) {
        return new SignUp(email, password, new Role(roleStr));
    } 
}
