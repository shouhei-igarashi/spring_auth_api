package com.farmec.project.application.service.user.impl.sub;

import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.UserDetailsImpl;

public interface UserStatusSubQueryService {
    String getUserStatus(SignIn signIn);
    String getUserStatus(UserDetailsImpl userDetais);
}
