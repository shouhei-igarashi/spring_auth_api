package com.farmec.project.infrastructure.repository.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.farmec.project.application.infrastructure.UserRepository;
import com.farmec.project.domain.model.secure.MyUserDetails;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.SignUpResult;
import com.farmec.project.infrastructure.entity.user.User;
import com.farmec.project.infrastructure.repository.user.jpa.UserJpa;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final UserJpa userJpaRepository;
    
    public UserRepositoryImpl(UserJpa userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public SignUpResult save(SignUp signup) {
        User user = new User(signup);
        User resultUser = userJpaRepository.save(user);

        if (resultUser == null) {
            return new SignUpResult();
        }

        return new SignUpResult(resultUser.getEmail(), resultUser.getRole());
    }

    @Override
    public Boolean existsByEmail(SignUp signup){
        return userJpaRepository.existsByEmail(signup.getEmail().toString());
    }
    @Override
    public MyUserDetails findByEmail(String email){
        Optional<User> user = userJpaRepository.findByEmail(email);
        
        if (user.isEmpty()) {
            return new MyUserDetails();
        }

        return new MyUserDetails(user.get().getEmail(), user.get().getPassword(), user.get().getRole());
    }
}
