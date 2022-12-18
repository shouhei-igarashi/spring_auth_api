package com.farmec.project.domain.type.account.name;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FirstName {
    
    @NotBlank
    @Size(max = 50)
    String value;

    public FirstName() {}

    public FirstName(String firstName) {
        value = firstName;
    }

    @Override
    public String toString() {
        return value;
    }
}
