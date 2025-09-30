package com.frknozbek.controller;

import com.frknozbek.dto.DtoAccount;
import com.frknozbek.dto.DtoAccountIU;

public interface IRestAccountController {
    RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
