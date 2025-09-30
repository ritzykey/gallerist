package com.frknozbek.controller;

import com.frknozbek.dto.DtoAddress;
import com.frknozbek.dto.DtoAddressIU;

public interface IRestAddressController {
    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
