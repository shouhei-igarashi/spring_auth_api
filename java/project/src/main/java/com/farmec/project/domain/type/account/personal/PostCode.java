package com.farmec.project.domain.type.account.personal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PostCode {
    @NotBlank(message = "郵便番号を入力してください")
    @Pattern(regexp = "([0-9]{3}[0-9]{4})?", message = "郵便番号の形式で入力してください")
    @Size(min = 7, max = 7, message = "桁数は7桁で入力してください")
    String value;

    public PostCode() {}

    public PostCode(String postCode) {
        value = postCode;
    }

    public Integer toInteger() {
        return Integer.decode(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
