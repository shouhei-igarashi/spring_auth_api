package com.farmec.project.domain.type.secure.auth;

import java.util.Arrays;

public enum Roles {
    ROLE_USER("user"),
    ROLE_MODERATOR("moderator"),
    ROLE_ADMIN("admin");

    private final String role;

    private Roles(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }

    public static String getName(String key) {
        return Arrays.stream(Roles.values())
            .filter(role -> role.getRoleName().equals(key))
            .findFirst().orElse(Roles.ROLE_USER).name();
    }
}