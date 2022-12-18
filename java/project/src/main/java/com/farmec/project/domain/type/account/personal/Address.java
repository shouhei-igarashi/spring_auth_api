package com.farmec.project.domain.type.account.personal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Address {
    
    @NotBlank
    @Size(max = 500)
    String value;

    public Address() {}

    public Address(String address) {
        value = address;
    }

    @Override
    public String toString() {
        return value;
    }
}
