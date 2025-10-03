
package com.frknozbek.utils;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.frknozbek.GalleristApplicationStarter;
import com.frknozbek.enums.CurrencyType;
import com.frknozbek.model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(classes = { GalleristApplicationStarter.class })
public class DateUtilsTest {

    @Test
    public void testGetCurrentDate() throws ParseException {
        // Given
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date testDate = sdf.parse("15-12-2023");

        // When
        String result = DateUtils.getCurrentDate(testDate);

        // Then
        assertEquals("15-12-2023", result);

        System.out.println(new Account("123123", "Tr124124124", new BigDecimal(124312), CurrencyType.TL));
    }

    @Test
    public void testGetCurrentDateWithDifferentDate() throws ParseException {
        // Given
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date testDate = sdf.parse("01-01-2024");

        // When
        String result = DateUtils.getCurrentDate(testDate);

        // Then
        assertEquals("01-01-2024", result);
    }

    @Test
    public void testConstructorThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            new DateUtils();
        });
    }
}