package com.farmec.project.domain.type;

import javax.validation.constraints.*;

public class Password {

    @NotBlank
    @Size(min = 6, max = 40)
    String value;

    public Password(String password) {
        value = password;
    }

    @Override
    public String toString() {
        return value;
    }
}
