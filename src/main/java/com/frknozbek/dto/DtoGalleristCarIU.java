package com.frknozbek.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCarIU {

    @NotNull
    private String galleristId;

    @NotNull
    private String carId;

}
