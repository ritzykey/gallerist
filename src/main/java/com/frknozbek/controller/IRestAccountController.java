package com.frknozbek.controller;

import java.util.List;

import com.frknozbek.dto.DtoAccount;
import com.frknozbek.dto.DtoAccountIU;

public interface IRestAccountController {
    RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

    RootEntity<DtoAccount> getAccountById(String accountNo);

    RootEntity<List<DtoAccount>> listAccount();
}
