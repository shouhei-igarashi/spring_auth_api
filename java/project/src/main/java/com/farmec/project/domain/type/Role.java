package com.farmec.project.domain.type;

public class Role {
    String value;

    public Role() {
        value = "";
    }

    public Role(String role) {
        value = role;
    }

    public String name() {
        if ("admin".equals(value)) {
            return Roles.ROLE_USER.name();
        } 
        
        if ("mod".equals(value)) {
            return Roles.ROLE_MODERATOR.name();
        }
        
        return Roles.ROLE_USER.name();
    }

    @Override
    public String toString() {
        return value;
    }
}
