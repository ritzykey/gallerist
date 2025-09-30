package com.frknozbek.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.frknozbek.enums.CarStatusType;
import com.frknozbek.enums.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car  extends BaseEntity{

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
