package com.farmec.project.domain.model.security;

public class ResultSignUp {
    private Boolean result;
    private String message;

    public ResultSignUp(Boolean result, String message) {
        this.result = result;
        
        if (message.isEmpty()) {
            this.message = result ? "登録成功":"登録失敗";

            return;
        }

        this.message = message;
    }

    public Boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
