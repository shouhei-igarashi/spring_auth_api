package com.farmec.project.application.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farmec.project.domain.model.security.MyUserDetails;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.repository.security.SecurityUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SecurityUserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(SecurityUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("アカウントが見つかりませんでした。 " + email));

    return MyUserDetails.build(user);
    }
}
