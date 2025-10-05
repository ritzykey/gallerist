package com.frknozbek.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.frknozbek.enums.CurrencyType;
import com.frknozbek.model.Car;

@DataMongoTest
public class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @Test
    void testFindAllByProductionYear() {

        List<Car> byProductionYearCars = carRepository.findAllByProductionYear(2017);

        byProductionYearCars.forEach(car -> {
            assertEquals(2017, car.getProductionYear());
        });

    }

    @Test
    void testFindAllByProductionYearPage() {

        Page<Car> byProductionYearCars = carRepository.findAllByProductionYear(2017, PageRequest.of(1, 2));

        assertEquals(2,byProductionYearCars.getPageable().getPageSize());
        assertEquals(3, byProductionYearCars.getTotalElements());


        byProductionYearCars.forEach(car -> {
            assertEquals(2017, car.getProductionYear());
        });

    }

    @Test
    void testFindAllByProductionYearAndCurrencyType() {

        List<Car> byProductionYearAndCurrencyTypeCars = carRepository.findAllByProductionYearAndCurrencyType(2017, CurrencyType.USD);

        byProductionYearAndCurrencyTypeCars.forEach(car -> {
            assertEquals(2017, car.getProductionYear());
            assertEquals("USD", car.getCurrencyType().name());
        });

    }
}
