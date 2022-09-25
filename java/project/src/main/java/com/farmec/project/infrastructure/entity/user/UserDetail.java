package com.farmec.project.infrastructure.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.*;

//@Entity
//@Table(name = "user_details", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class UserDetail {
    //@Id
    //@NotBlank
    //@Size(max = 50)
    //@Email
    private String email;

    //@NotBlank
    //@Size(max = 120)
    //@Column(name="full_name")
    private String fullName;

    //@NotBlank
    //@Size(max = 120)
    //@Column(name="account_name")
    private String accountName;
    
    public UserDetail() {
    }

    public UserDetail(String email, String password, String role) {
        this.email = email;
    }

   
}
