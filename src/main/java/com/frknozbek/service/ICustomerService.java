package com.frknozbek.service;

import com.frknozbek.dto.DtoCustomer;
import com.frknozbek.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU input);

}
