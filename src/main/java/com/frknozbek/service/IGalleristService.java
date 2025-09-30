package com.frknozbek.service;

import com.frknozbek.dto.DtoGallerist;
import com.frknozbek.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU input);

}
