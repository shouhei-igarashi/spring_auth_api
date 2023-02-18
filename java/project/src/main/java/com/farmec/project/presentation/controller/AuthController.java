package com.farmec.project.presentation.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farmec.project.application.service.secure.SignInQueryService;
import com.farmec.project.application.service.secure.signup.SignUpService;
import com.farmec.project.domain.model.secure.MyUserDetails;
import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.domain.model.secure.SignUpResult;
import com.farmec.project.presentation.config.jwt.JwtUtils;
import com.farmec.project.presentation.payload.response.JwtToken;
import com.farmec.project.presentation.payload.response.RoleMessage;

import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final String userRole = "user";
    private final String adminRole = "admin";

    private final JwtUtils jwtUtils;

    private final SignInQueryService signInService;
    private final SignUpService signUpService;

    public AuthController(SignInQueryService signInService, SignUpService signUpService, JwtUtils jwtUtils) {
        this.signInService = signInService;
        this.signUpService = signUpService;
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(@Valid @RequestBody SignIn signIn) {
        MyUserDetails userDetails = signInService.getMyUserDetails(signIn);
        String jwt = jwtUtils.generateJwtToken(userDetails.getAuthentication());

        return ResponseEntity.status(HttpStatus.OK)
            .body(new JwtToken(jwt, userDetails.getUsername(), userDetails.getRole()));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@Valid @RequestBody SignUp signUp) {
        SignUpResult signUpResult = signUpService.createUser(signUp, userRole);
        RoleMessage roleMessage = new RoleMessage(signUpResult.getMessage());

        return signUpResult.isSuccess()
                ? ResponseEntity.status(HttpStatus.CREATED).body(roleMessage)
                : ResponseEntity.badRequest().body(roleMessage);
    }

    @RequestMapping(value = "/signup/admin", method = RequestMethod.POST)
    public ResponseEntity<?> signupAdmin(@Valid @RequestBody SignUp signUp) {
        SignUpResult signUpResult = signUpService.createUser(signUp, adminRole);
        RoleMessage roleMessage = new RoleMessage(signUpResult.getMessage());

        return signUpResult.isSuccess()
                ? ResponseEntity.status(HttpStatus.CREATED).body(roleMessage)
                : ResponseEntity.badRequest().body(roleMessage);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<?> check() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
