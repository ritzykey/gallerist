package com.frknozbek.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frknozbek.dto.CurrencyRatesResponse;
import com.frknozbek.dto.DtoCar;
import com.frknozbek.dto.DtoCustomer;
import com.frknozbek.dto.DtoGallerist;
import com.frknozbek.dto.DtoSaledCar;
import com.frknozbek.dto.DtoSaledCarIU;
import com.frknozbek.enums.CarStatusType;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.model.Car;
import com.frknozbek.model.Customer;
import com.frknozbek.model.SaledCar;
import com.frknozbek.repository.AccountRepository;
import com.frknozbek.repository.CarRepository;
import com.frknozbek.repository.CustomerRepository;
import com.frknozbek.repository.GalleristRepository;
import com.frknozbek.repository.SaledCarRepository;
import com.frknozbek.service.ICurrencyRatesService;
import com.frknozbek.service.ISaledCarService;
import com.frknozbek.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {

        CurrencyRatesResponse currenyRatesResponse = currencyRatesService.getCurrenyRatesResponse(
                DateUtils.getCurrentDate(new Date()),
                DateUtils.getCurrentDate(new Date()));

        BigDecimal usd = new BigDecimal(currenyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

        return customerUSDAmount;
    }

    public boolean checkCarStatus(String carId) {

        Optional<Car> optCar = carRepository.findById(carId);

        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, carId));
        }

        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
            return false;
        }

        return true;

    }

    public BigDecimal remaningCustomerAmouunt(Customer customer, Car car) {

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currenyRatesResponse = currencyRatesService.getCurrenyRatesResponse(
                DateUtils.getCurrentDate(new Date()),
                DateUtils.getCurrentDate(new Date()));

        BigDecimal usd = new BigDecimal(currenyRatesResponse.getItems().get(0).getUsd());

        return remaningCustomerUSDAmount.multiply(usd);

    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId()));
        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

        if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
                || customerUSDAmount.compareTo(optCar.get().getPrice()) < 0) {
            return false;

        }

        return true;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
            throw new BaseException(
                    new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId()));
        }

        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, customerRepository
                    .findById(dtoSaledCarIU.getCustomerId()).orElse(null).getAccount().getAmount().toString()));
        }


        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerAmouunt(customer, car));
        accountRepository.save(customer.getAccount());

        return toDTO(savedSaledCar);
    }

    public DtoSaledCar toDTO(SaledCar saledCar) {
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCar dtoCar = new DtoCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);

        dtoSaledCar.setCreateTime(new Date());
        dtoSaledCar.setDtoCar(dtoCar);
        dtoSaledCar.setDtoCustomer(dtoCustomer);
        dtoSaledCar.setDtoGallerist(dtoGallerist);

        return dtoSaledCar;
    }

}
