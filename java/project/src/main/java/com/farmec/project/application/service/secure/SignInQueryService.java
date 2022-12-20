package com.farmec.project.application.service.secure;

import com.farmec.project.domain.model.secure.MyUserDetails;
import com.farmec.project.domain.model.secure.SignIn;

public interface SignInQueryService {
    MyUserDetails getMyUserDetails(SignIn signIn);
}
