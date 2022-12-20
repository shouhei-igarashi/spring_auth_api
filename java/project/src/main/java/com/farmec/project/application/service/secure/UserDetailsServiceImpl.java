package com.farmec.project.application.service.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.domain.model.secure.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUserDetails myUserDetails = userRepository.findByEmail(email);
        myUserDetails.checkUser();

        return myUserDetails;
    }
}
