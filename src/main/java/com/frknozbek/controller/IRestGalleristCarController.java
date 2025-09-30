package com.frknozbek.controller;

import com.frknozbek.dto.DtoGalleristCar;
import com.frknozbek.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> savGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
