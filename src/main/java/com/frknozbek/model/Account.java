package com.frknozbek.model;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.frknozbek.enums.CurrencyType;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Account extends BaseEntity {

    private String accountNo;

    private String iban;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal amount;

    private CurrencyType currencyType;
}
