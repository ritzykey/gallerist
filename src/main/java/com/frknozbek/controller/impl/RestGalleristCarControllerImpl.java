package com.frknozbek.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frknozbek.controller.IRestGalleristCarController;
import com.frknozbek.controller.RootEntity;
import com.frknozbek.dto.DtoGalleristCar;
import com.frknozbek.dto.DtoGalleristCarIU;
import com.frknozbek.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist_car")
public class RestGalleristCarControllerImpl extends RootEntity<DtoGalleristCar> implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> savGalleristCar(@RequestBody @Valid DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.savGalleristCar(dtoGalleristCarIU));
    }

}
