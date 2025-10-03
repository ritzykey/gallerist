package com.frknozbek.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

import com.frknozbek.enums.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account extends BaseEntity {
    
    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;
}
