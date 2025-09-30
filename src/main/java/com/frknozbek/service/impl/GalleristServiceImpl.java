package com.frknozbek.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frknozbek.dto.DtoAddress;
import com.frknozbek.dto.DtoGallerist;
import com.frknozbek.dto.DtoGalleristIU;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.model.Address;
import com.frknozbek.model.Gallerist;
import com.frknozbek.repository.AddressRepository;
import com.frknozbek.repository.GalleristRepository;
import com.frknozbek.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());

        if (optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId()));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);

        gallerist.setAddress(optAddress.get());

        return gallerist;

    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU input) {

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        dtoGallerist.setCreateTime(new Date());

        Gallerist savedGallerist = galleristRepository.save(createGallerist(input));

        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }

}
