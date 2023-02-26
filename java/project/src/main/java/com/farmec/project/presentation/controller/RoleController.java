package com.farmec.project.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farmec.project.application.service.user.UserService;
import com.farmec.project.application.service.user.UserStatusService;
import com.farmec.project.domain.model.secure.UserDetailsImpl;
import com.farmec.project.presentation.payload.response.ErrorResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/role")
@RestController
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final UserStatusService userStatusService;

    public RoleController(UserService userService, UserStatusService userStatusService) {
        this.userService = userService;
        this.userStatusService = userStatusService;
    }
    
    /**
     * Admin権限であるかをチェック
     * @param userDetails UserDetailsImpl
     * @return ResponseEntity
     */
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<?> roleAdmin(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userStatusService.isActiveStatus(userDetails)
            && userService.isAdminPrivileges(userDetails)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * User権限であるかをチェック
     * @param userDetails UserDetailsImpl
     * @return ResponseEntity
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<?> roleUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userStatusService.isActiveStatus(userDetails)
            && userService.isUserPrivileges(userDetails)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return new ErrorResponse("500", "サーバエラーが発生しました");
    }
}
