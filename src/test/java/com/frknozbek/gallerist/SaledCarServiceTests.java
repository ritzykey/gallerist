package com.frknozbek.gallerist;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.frknozbek.GalleristApplicationStarter;
import com.frknozbek.dto.DtoSaledCar;
import com.frknozbek.enums.CarStatusType;
import com.frknozbek.model.Car;
import com.frknozbek.model.Customer;
import com.frknozbek.model.Gallerist;
import com.frknozbek.model.SaledCar;
import com.frknozbek.service.impl.SaledCarServiceImpl;

@SpringBootTest(classes = { GalleristApplicationStarter.class })
public class SaledCarServiceTests {

    @Autowired
    private SaledCarServiceImpl saledCarService;

    @Test
    public void testToDTO() {
        SaledCar saledCar = new SaledCar();

        Car car = new Car();
        car.setBrand("Toyota");
        car.setCarStatusType(CarStatusType.SALABLE);
        car.setModel("Corollar");

        Customer customer = new Customer();
        customer.setFirstName("Furkan");
        customer.setLastName("OZBEK");

        saledCar.setCreateTime(new Date());
        saledCar.setCar(car);
        saledCar.setCustomer(customer);
        saledCar.setGallerist(new Gallerist());

        DtoSaledCar dtoSaledCar = saledCarService.toDTO(saledCar);

        System.out.println("Brand: " + dtoSaledCar.getDtoCar().getBrand());

        assertTrue(dtoSaledCar.getDtoCar().getBrand().equals("Toyota"));
    }
}
