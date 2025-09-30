package com.frknozbek.controller;

import com.frknozbek.dto.DtoCustomer;
import com.frknozbek.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> savCustomer(DtoCustomerIU input);

}
