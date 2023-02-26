package com.farmec.project.infrastructure.repository.user;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.domain.model.secure.UserDetailsImpl;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final UserJpa userJpa;
    
    public UserRepositoryImpl(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    @Override
    public Boolean save(SignUp signUp) {
        User user = new User(signUp);
        User resultUser = userJpa.save(user);

        if (resultUser == null) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean existsByEmail(String email){
        return userJpa.existsByEmail(email);
    }

    @Override
    public Boolean existsByUserDetailsImpl(UserDetailsImpl userDetails) {
        List<User> users = userJpa.findByEmailAndRole(userDetails.getUsername(), userDetails.getRole());
        return users.size() > 0 ? true : false;
    }

    @Override
    public UserDetailsImpl findByEmail(String email){
        User user = userJpa.findByEmail(email);
        
        if (user == null) {
            return new UserDetailsImpl();
        }

        return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRole());
    }
}
