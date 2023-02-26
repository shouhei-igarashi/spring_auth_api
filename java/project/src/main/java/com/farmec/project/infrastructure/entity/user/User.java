package com.farmec.project.infrastructure.entity.user;

import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.UserDetailsImpl;
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

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;
    
    public User() {
    }

    public User(SignUp signUp) {
        this.email = signUp.getEmail().toString();
        this.password = signUp.getPassword().getEncodePassword();
        this.role =  signUp.getRole().toString();
    }

    public User(UserDetailsImpl userDetails) {
        this.email = userDetails.getUsername();
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

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}