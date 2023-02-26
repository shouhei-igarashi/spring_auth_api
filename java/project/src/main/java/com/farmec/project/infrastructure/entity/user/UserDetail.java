package com.farmec.project.infrastructure.entity.user;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.farmec.project.domain.model.account.Account;

@Entity
@Table(name = "user_details", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class UserDetail {
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

    @OneToOne
    @JoinColumn(name = "email")
    private User user;

    @CreationTimestamp
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_date")
    private Date updateDate;
    
    public UserDetail() {
    }

    public UserDetail(Account account, User user) {
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
