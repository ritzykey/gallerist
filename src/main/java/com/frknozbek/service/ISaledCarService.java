package com.frknozbek.service;

import com.frknozbek.dto.DtoSaledCar;
import com.frknozbek.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);

}
