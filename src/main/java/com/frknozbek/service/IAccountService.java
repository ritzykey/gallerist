package com.frknozbek.service;

import java.util.List;

import com.frknozbek.dto.DtoAccount;
import com.frknozbek.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

    public String sayHello();

    public List<DtoAccount> listAccount();

    public DtoAccount getAccountById(String accountNo);

}
