package com.frknozbek.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {

    @Id
    private String id;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createTime;
}
