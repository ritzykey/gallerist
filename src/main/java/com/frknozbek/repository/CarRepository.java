package com.frknozbek.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frknozbek.model.Car;
import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends MongoRepository<Car, String> {

    Optional<List<Car>> findAllByProductionYear(Integer productionYear);

}
