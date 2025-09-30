package com.frknozbek.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frknozbek.dto.DtoAddress;
import com.frknozbek.dto.DtoCar;
import com.frknozbek.dto.DtoGallerist;
import com.frknozbek.dto.DtoGalleristCar;
import com.frknozbek.dto.DtoGalleristCarIU;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.model.Car;
import com.frknozbek.model.Gallerist;
import com.frknozbek.model.GalleristCar;
import com.frknozbek.repository.CarRepository;
import com.frknozbek.repository.GalleristCarRepository;
import com.frknozbek.repository.GalleristRepository;
import com.frknozbek.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());

        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId()));
        }

        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId()));
        }

        GalleristCar galleristCar = new GalleristCar();

        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;
    }

    @Override
    public DtoGalleristCar savGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        dtoGalleristCar.setCreateTime(new Date());

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        DtoAddress dtoAddress = new DtoAddress();

        Gallerist gallerist = savedGalleristCar.getGallerist();
        Car car = savedGalleristCar.getCar();

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(gallerist, dtoGallerist);
        BeanUtils.copyProperties(car, dtoCar);

        BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);

        dtoGallerist.setAddress(dtoAddress);

        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

}
