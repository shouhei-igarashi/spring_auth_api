package com.farmec.project.domain.type.secure.auth;

public enum Status {
    ACTIVE("active"),
    DRAW("draw"),
    PAUSE("pause");

    private Status(String status) {
        this.status = status;
    }

    private final String status;

    public String getStatus() {
        return status;
    }
}
