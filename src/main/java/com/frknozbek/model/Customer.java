package com.frknozbek.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthOfDate;

    @DocumentReference(lazy = true)
    private Address address;

    @DocumentReference()
    private Account account;
}
