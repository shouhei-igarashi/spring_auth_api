package com.farmec.project.presentation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farmec.project.application.service.account.AccountRecordService;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.domain.model.secure.UserDetailsImpl;
import com.farmec.project.domain.type.key.EmailAddress;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserDetailController {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailController.class);
    private final AccountRecordService accountRecordService;

    public UserDetailController(AccountRecordService accountRecordService) {
        this.accountRecordService = accountRecordService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetailsImpl myUserDetails,  @Valid @RequestBody Account account) {
        try {
            account.build(new EmailAddress(myUserDetails.getUsername()));
            
            if (accountRecordService.createAccount(account)) {
                return ResponseEntity.status(HttpStatus.CREATED).build() ;
            }      
        } catch (Exception e) {
            logger.error("user登録失敗", e);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<?> find(@AuthenticationPrincipal UserDetailsImpl myUserDetails,  @Valid @RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}