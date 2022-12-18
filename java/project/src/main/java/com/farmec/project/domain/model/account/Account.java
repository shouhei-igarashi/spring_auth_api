package com.farmec.project.domain.model.account;

import com.farmec.project.domain.type.account.name.*;
import com.farmec.project.domain.type.account.personal.*;
import com.farmec.project.domain.type.key.EmailAddress;

public class Account {
    private EmailAddress email;
   
    private FirstName firstName;
    private LastName lastName;

    private PostCode postCode;
    private Address address;
    private PhoneNumber phoneNumber;
    
    public void setAuthUserEMailAddress(EmailAddress email) {
        this.email = email;
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
