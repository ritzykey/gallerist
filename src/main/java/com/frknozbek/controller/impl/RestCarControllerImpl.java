package com.frknozbek.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frknozbek.controller.IRestCarController;
import com.frknozbek.controller.RestBaseController;
import com.frknozbek.controller.RootEntity;
import com.frknozbek.dto.DtoCar;
import com.frknozbek.dto.DtoCarIU;
import com.frknozbek.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody  DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }

}
