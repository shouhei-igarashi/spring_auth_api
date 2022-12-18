package com.farmec.project.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.farmec.project.application.service.security.SignInService;
import com.farmec.project.application.service.security.SignUpService;
import com.farmec.project.domain.model.security.MyUserDetails;
import com.farmec.project.domain.model.security.ResultSignUp;
import com.farmec.project.presentation.config.jwt.JwtUtils;
import com.farmec.project.domain.model.security.SignIn;
import com.farmec.project.domain.model.security.SignUp;
import com.farmec.project.presentation.payload.security.response.JwtToken;
import com.farmec.project.presentation.payload.security.response.RoleMessage;

import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtUtils jwtUtils;

    private final SignInService signInService;
    private final SignUpService signUpService;

    @Autowired
    public AuthController(SignInService signInService, SignUpService signUpService, JwtUtils jwtUtils) {
        this.signInService = signInService;
        this.signUpService = signUpService;
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignIn signIn) {
        MyUserDetails userDetails = signInService.getMyUserDetails(signIn);

        return ResponseEntity.ok(new JwtToken(userDetails.getJwt(),
                userDetails.getUsername(),
                userDetails.getRole()));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUp signUp) {
        ResultSignUp resultSignUp = signUpService.createUser(signUp);
        RoleMessage roleMessage = new RoleMessage(resultSignUp.getMessage());

        return resultSignUp.getResult()
                ? ResponseEntity.ok(roleMessage)
                : ResponseEntity.badRequest().body(roleMessage);
    }

    @RequestMapping(value = "/signout", method = RequestMethod.POST)
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new RoleMessage("You've been signed out!"));
    }
}
