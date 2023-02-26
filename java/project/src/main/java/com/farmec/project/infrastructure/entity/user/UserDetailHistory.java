package com.farmec.project.infrastructure.entity.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.farmec.project.domain.model.account.Account;

import javax.persistence.*;

@Entity
@Table(name = "user_detail_histories")
public class UserDetailHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 120)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 120)
    @Column(name="last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Size(max = 7)
    @Column(name="post_code", nullable = false)
    private String postCode;

    @NotBlank
    @Size(max = 500)
    @Column(name="current_address", nullable = false)
    private String currentAddress;

    @NotBlank
    @Size(max = 15)
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "email")
    private User user;
    
    public UserDetailHistory() {
    }

    public UserDetailHistory(Account account, User user) {
        this.user = user;
        firstName = account.getFirstName().toString();
        lastName = account.getLastName().toString();
        postCode = account.getPostCode().toString();
        currentAddress = account.getAddress().toString();
        phoneNumber = account.getPhoneNumber().toString();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User getUser() {
        return user;
    }
}
