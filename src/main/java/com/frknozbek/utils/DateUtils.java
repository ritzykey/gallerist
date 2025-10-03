package com.frknozbek.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

    DateUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static String getCurrentDate(Date date) {

        log.info("DateUtils.getCurrentDate logger info");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);

    }

}
