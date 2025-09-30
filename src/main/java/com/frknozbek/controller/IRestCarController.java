package com.frknozbek.controller;

import com.frknozbek.dto.DtoCar;
import com.frknozbek.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

}
