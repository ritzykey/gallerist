package com.frknozbek.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frknozbek.model.GalleristCar;

@Repository
public interface GalleristCarRepository extends MongoRepository<GalleristCar, String> {

}
