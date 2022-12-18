package com.farmec.project.infrastructure.entity.user;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.farmec.project.domain.model.account.Account;
import com.farmec.project.domain.model.security.SignUp;
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User {

    @Id
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Size(max = 20)
    @Column(name = "role", nullable = false)
    private String role;
    
    public User() {
    }

    public User(SignUp signUp) {
        this.email = signUp.getEmail().toString();
        this.password = signUp.getPassword().getEncodePassword();
        this.role =  signUp.getRole().name();
    }

    public User(Account account) {
        this.email = account.getEmail().toString();
        this.password = "detail";
        this.role = "detail";
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}