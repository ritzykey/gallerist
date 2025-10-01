package com.frknozbek.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCarIU {

    @NotNull
    private String customerId;

    @NotNull
    private String galleristId;

    @NotNull
    private String carId;

}
