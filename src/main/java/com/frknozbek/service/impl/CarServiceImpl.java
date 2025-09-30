package com.frknozbek.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frknozbek.dto.DtoCar;
import com.frknozbek.dto.DtoCarIU;
import com.frknozbek.model.Car;
import com.frknozbek.repository.CarRepository;
import com.frknozbek.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCar(DtoCarIU dtoCarIU) {
        Car car = new Car();

        car.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCarIU, car);

        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        dtoCar.setCreateTime(new Date());

        Car savedCar = carRepository.save(createCar(dtoCarIU));

        BeanUtils.copyProperties(savedCar, dtoCar);

        return dtoCar;
    }

}
