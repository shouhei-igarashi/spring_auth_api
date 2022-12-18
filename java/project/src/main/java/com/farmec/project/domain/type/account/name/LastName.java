package com.farmec.project.domain.type.account.name;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LastName {
    
    @NotBlank
    @Size(max = 50)
    String value;

    public LastName() {}

    public LastName(String lastName) {
        value = lastName;
    }

    @Override
    public String toString() {
        return value;
    }
}
