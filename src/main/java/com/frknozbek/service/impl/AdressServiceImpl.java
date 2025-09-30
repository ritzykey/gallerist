package com.frknozbek.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frknozbek.dto.DtoAddress;
import com.frknozbek.dto.DtoAddressIU;
import com.frknozbek.model.Address;
import com.frknozbek.repository.AddressRepository;
import com.frknozbek.service.IAddressService;

@Service
public class AdressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU, address);

        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU input) {

        DtoAddress dtoAddress = new DtoAddress();

        Address savedAddress = addressRepository.save(createAddress(input));

        BeanUtils.copyProperties(savedAddress, dtoAddress);
        
        return dtoAddress;
    }

}
