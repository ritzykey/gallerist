package com.frknozbek.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frknozbek.model.Car;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

}
