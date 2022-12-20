package com.farmec.project.domain.model.secure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.security.auth.message.AuthException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;

    @JsonIgnore
    private String password;

    private String role;

    private Collection<? extends GrantedAuthority> authorities;

    private Boolean isNotFound;

    private Authentication authentication;

    public MyUserDetails() {
        isNotFound = true;
    }

    public MyUserDetails(String username) {
        this.username = username;
        isNotFound = true;
    }

    public MyUserDetails(String username, String password) {
        this.username = username;
        this.password = password;
        
        isNotFound = false;
    }

    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;

        isNotFound = false;
    }

    public MyUserDetails(String username, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        this.username = username;
        this.password = password;
        this.authorities = authorities;

        isNotFound = false;
    }

    public static MyUserDetails build(Authentication authentication) {
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        myUserDetails.setAuthentication(authentication);

        return myUserDetails;
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

    public Authentication getAuthentication() {
        return authentication;
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

    public void checkUser() {
        if (isNotFound) {
           throw new UsernameNotFoundException("アカウントが見つかりませんでした。 " + username);
        }
    }

    private void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
