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

import com.farmec.project.application.service.account.AccountRecordService;
import com.farmec.project.domain.model.account.Account;
import com.farmec.project.domain.model.secure.MyUserDetails;
import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.presentation.payload.response.RoleMessage;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final AccountRecordService accountRecordService;

    public UserController(AccountRecordService accountRecordService) {
        this.accountRecordService = accountRecordService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@AuthenticationPrincipal MyUserDetails myUserDetails,  @Valid @RequestBody Account account) {
        account.setAuthUserEMailAddress(new EmailAddress(myUserDetails.getUsername()));
        Boolean isSuccess = accountRecordService.createAccount(account);
        return  isSuccess ? 
            ResponseEntity.status(HttpStatus.CREATED).body(new RoleMessage("登録成功")) 
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RoleMessage("登録失敗"));
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ResponseEntity<?> find(@AuthenticationPrincipal MyUserDetails myUserDetails,  @Valid @RequestBody Account account) {
        account.setAuthUserEMailAddress(new EmailAddress(myUserDetails.getUsername()));
        Boolean isSuccess = accountRecordService.createAccount(account);
        return  isSuccess ? 
            ResponseEntity.status(HttpStatus.CREATED).body(new RoleMessage("登録成功")) 
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RoleMessage("登録失敗"));
    }
}
