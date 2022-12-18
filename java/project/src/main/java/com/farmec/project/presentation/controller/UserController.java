package com.farmec.project.presentation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.farmec.project.domain.model.security.MyUserDetails;
import com.farmec.project.domain.type.key.EmailAddress;
import com.farmec.project.presentation.payload.security.response.RoleMessage;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/detail")
public class UserController {
    private final AccountRecordService accountRecordService;

    @Autowired
    public UserController(AccountRecordService accountRecordService) {
        this.accountRecordService = accountRecordService;
    }

    
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ResponseEntity<?> index() {
        
        return  ResponseEntity.ok(new RoleMessage("user成功したよ!"));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> regist(@AuthenticationPrincipal MyUserDetails myUserDetails,  @Valid @RequestBody Account account) {
        account.setAuthUserEMailAddress(new EmailAddress(myUserDetails.getUsername()));
        accountRecordService.registAccount(account);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new RoleMessage("登録成功"));
    }
}
