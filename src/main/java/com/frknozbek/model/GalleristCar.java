package com.frknozbek.model;


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
public class GalleristCar extends BaseEntity {

    @DocumentReference(lazy = true)
    private Gallerist gallerist;

    @DocumentReference(lazy = true)
    private Car car;

}
