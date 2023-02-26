package com.farmec.project.domain.model.secure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
    private String username;

    @JsonIgnore
    private String password;

    private String role;

    private Collection<? extends GrantedAuthority> authorities;

    private Boolean isNotFound;

    private Authentication authentication;

    public UserDetailsImpl() {
        isNotFound = true;
    }

    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
        
        isNotFound = false;
    }

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;

        isNotFound = false;
    }

    public UserDetailsImpl(String username, String password, String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        this.username = username;
        this.password = password;
        this.authorities = authorities;

        isNotFound = false;
    }

    public static UserDetailsImpl build(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        userDetails.setAuthentication(authentication);

        return userDetails;
    }

    public UserDetailsImpl build(String role) {
        return new UserDetailsImpl(this.username, this.password, role);
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
           
        UserDetailsImpl user = (UserDetailsImpl) o;
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
