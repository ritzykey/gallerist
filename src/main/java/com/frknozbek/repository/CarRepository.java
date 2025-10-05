package com.frknozbek.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.frknozbek.model.Car;
import java.util.List;
import com.frknozbek.enums.CurrencyType;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {

    List<Car> findAllByProductionYear(Integer productionYear);
    Page<Car> findAllByProductionYear(Integer productionYear, Pageable pageable);

    List<Car> findAllByProductionYearAndCurrencyType(Integer productionYear, CurrencyType currencyType);

}
