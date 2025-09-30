package com.frknozbek.service;

import com.frknozbek.dto.DtoCar;
import com.frknozbek.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);

}
