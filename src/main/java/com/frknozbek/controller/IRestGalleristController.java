package com.frknozbek.controller;

import com.frknozbek.dto.DtoGallerist;
import com.frknozbek.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
