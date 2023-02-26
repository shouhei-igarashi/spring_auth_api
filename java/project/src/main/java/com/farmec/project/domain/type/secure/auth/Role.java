package com.farmec.project.domain.type.secure.auth;

public class Role {
    String value;

    public Role() {
        value = "";
    }

    public Role(String role) {
        value = role;
    }

    @Override
    public String toString() {
        return Roles.getName(value);
    }
}
