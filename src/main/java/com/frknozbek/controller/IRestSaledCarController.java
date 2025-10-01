package com.frknozbek.controller;

import com.frknozbek.dto.DtoSaledCar;
import com.frknozbek.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);

}
