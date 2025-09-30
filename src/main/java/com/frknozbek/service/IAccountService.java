package com.frknozbek.service;

import com.frknozbek.dto.DtoAccount;
import com.frknozbek.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU); 

}
