package com.farmec.project.presentation.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farmec.project.application.service.user.UserService;
import com.farmec.project.application.service.user.UserStatusService;
import com.farmec.project.domain.model.secure.UserDetailsImpl;
import com.farmec.project.domain.model.secure.SignIn;
import com.farmec.project.domain.model.secure.SignUp;
import com.farmec.project.presentation.config.jwt.JwtUtils;
import com.farmec.project.presentation.payload.response.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final UserStatusService userStatusService;

    public AuthController(UserService userService, UserStatusService userStatusService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.userStatusService = userStatusService;
        this.jwtUtils = jwtUtils;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(@Valid @RequestBody SignIn signIn) {
        if (userStatusService.isActiveStatus(signIn)) {
            UserDetailsImpl userDetails = userService.getUserDetails(signIn);
            String jwt = jwtUtils.create(userDetails.getAuthentication());

            return ResponseEntity.status(HttpStatus.OK)
                .body(new TokenResponse(jwt, userDetails.getUsername(), userDetails.getRole()));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new ErrorResponse("409", "ユーザは休止中か退会されています。"));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> signup(@Valid @RequestBody SignUp signUp) {
        if (userService.createUser(signUp) && userStatusService.createStatus(signUp)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @RequestMapping(value = "/signup/admin", method = RequestMethod.POST)
    public ResponseEntity<?> signupAdmin(@Valid @RequestBody SignUp signUp) {
        if (userService.createAdminUser(signUp) && userStatusService.createStatus(signUp)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse exceptionHandler(BadCredentialsException exception) {
        logger.error(exception.getMessage(), exception);
        return new ErrorResponse("403", "認証に失敗しました");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ErrorResponse("500", "サーバエラーが発生しました");
    }
}
