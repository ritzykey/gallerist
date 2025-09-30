package com.frknozbek.service;

import com.frknozbek.dto.DtoAddress;
import com.frknozbek.dto.DtoAddressIU;

public interface IAddressService {
    public DtoAddress saveAddress(DtoAddressIU input);
}
