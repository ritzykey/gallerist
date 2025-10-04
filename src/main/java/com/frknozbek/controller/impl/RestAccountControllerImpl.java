package com.frknozbek.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        System.out.println("testsetset");
        return ok(accountService.saveAccount(dtoAccountIU));
    }

    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoAccount>> listAccount() {
        return ok(accountService.listAccount());
    }

    @GetMapping("/{accountNo}")
    @Override
    public RootEntity<DtoAccount> getAccountById(@PathVariable String accountNo) {
        return ok(accountService.getAccountById(accountNo));
    }

}
