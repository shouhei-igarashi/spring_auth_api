package com.farmec.project.domain.model.account;

import com.farmec.project.domain.type.account.name.*;
import com.farmec.project.domain.type.account.personal.*;
import com.farmec.project.domain.type.key.EmailAddress;

/**
 * アカウントモデル
 */
public class Account {
    // Email
    private EmailAddress email;
   
    // 姓
    private FirstName firstName;
    
    // 名
    private LastName lastName;

    // 郵便番号
    private PostCode postCode;
    
    // 住所
    private Address address;
    
    // 電話番号
    private PhoneNumber phoneNumber;
    
    /**
     * Accountオブジェクト再生成
     * @param email EmailAddress
     * @return Account
     * @throws CloneNotSupportedException
     */
    public Account build(EmailAddress email) throws CloneNotSupportedException {
        this.email = email;
        return (Account)super.clone();
    }

    public EmailAddress getEmail() {
        return email;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public FullName getFullName() {
        return new FullName(firstName, lastName);
    }

    public PostCode getPostCode() {
        return postCode;
    }

    public Address getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
