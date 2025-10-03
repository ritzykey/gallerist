package com.frknozbek.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frknozbek.controller.IRestAccountController;
import com.frknozbek.controller.RestBaseController;
import com.frknozbek.controller.RootEntity;
import com.frknozbek.dto.DtoAccount;
import com.frknozbek.dto.DtoAccountIU;
import com.frknozbek.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        System.out.println("testsetset");
        return ok(accountService.saveAccount(dtoAccountIU));
    }

}
