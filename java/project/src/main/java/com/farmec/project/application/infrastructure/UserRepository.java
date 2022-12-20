package com.farmec.project.application.infrastructure;

import com.farmec.project.domain.model.account.Account;
import com.farmec.project.domain.model.secure.MyUserDetails;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.SignUpResult;

public interface UserRepository {
    SignUpResult save(SignUp signup);
    Boolean existsByEmail(SignUp signup);
    
    MyUserDetails findByEmail(String email);
}
