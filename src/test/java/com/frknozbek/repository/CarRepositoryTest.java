package com.frknozbek.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.frknozbek.model.Car;

@DataMongoTest
public class CarRepositoryTest {

    @Autowired
    CarRepository carRepository;

    @Test
    void testFindByProductionYearIsLikeIgnoreCase() {

        Optional<List<Car>> byProductionYearCars = carRepository.findAllByProductionYear(2017);

        byProductionYearCars.get().forEach(car -> {
            assertEquals(2017, car.getProductionYear());
        });

    }
}
