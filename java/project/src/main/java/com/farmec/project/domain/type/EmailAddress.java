package com.farmec.project.domain.type;

import javax.validation.constraints.*;

public class EmailAddress {
    
    @NotBlank
    @Size(max = 50)
    @Email
    String value;

    public EmailAddress() {}

    public EmailAddress(String email) {
        value = email;
    }

    @Override
    public String toString() {
        return value;
    }
}
