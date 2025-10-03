package com.frknozbek.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import com.frknozbek.GalleristApplicationStarter;
import com.frknozbek.enums.CurrencyType;


@SpringBootTest(classes = { GalleristApplicationStarter.class })
class AccountTest {

    @Test
    void testAccountConstructorAndGetters() {
        Account account = new Account("123", "TR123", new BigDecimal("1000.00"), CurrencyType.TL);
        
        assertEquals("123", account.getAccountNo());
        assertEquals("TR123", account.getIban());
        assertEquals(new BigDecimal("1000.00"), account.getAmount());
        assertEquals(CurrencyType.TL, account.getCurrencyType());
    }

    @Test
    void testAccountSetters() {
        Account account = new Account();
        
        account.setAccountNo("456");
        account.setIban("TR456");
        account.setAmount(new BigDecimal("2000.00"));
        account.setCurrencyType(CurrencyType.USD);

        assertEquals("456", account.getAccountNo());
        assertEquals("TR456", account.getIban());
        assertEquals(new BigDecimal("2000.00"), account.getAmount());
        assertEquals(CurrencyType.USD, account.getCurrencyType());
    }

    @Test
    void testToString() {
        Account account = new Account("789", "TR789", new BigDecimal("3000.00"), CurrencyType.USD);
        String toString = account.toString();
        System.out.println(toString);
        
        assertTrue(toString.contains("789"));
        assertTrue(toString.contains("TR789"));
        assertTrue(toString.contains("3000"));
        assertTrue(toString.contains("USD"));
    }
}