package com.farmec.project.domain.model.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.farmec.project.infrastructure.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

    private String role;

    private String jwt;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        
    }

    public static MyUserDetails build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new MyUserDetails(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getRole() {
        authorities.forEach(authority -> role = authority.getAuthority());

        return role;
    }

    public String getJwt() {
        return jwt;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
            
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
           
        MyUserDetails user = (MyUserDetails) o;
        return Objects.equals(username, user.username);
    }

    public void setJwtToken (String jwt)
    {
        this.jwt = jwt; 
    }
}
