package com.frknozbek.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frknozbek.enums.CurrencyType;
import com.frknozbek.model.Account;

@SpringBootTest
@Testcontainers
@TestPropertySource(properties = {
    "evds2.key=TEST_DEFAULT_KEY"
})
public class AccountRepositoryMongoIT {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    static void setMongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);

    }

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testFindByAccountNo() throws JsonProcessingException {

        Account account = new Account();
        account.setAccountNo("43215");
        account.setAmount(new BigDecimal(123123));
        account.setCreateTime(new Date());
        account.setCurrencyType(CurrencyType.TL);
        account.setIban("TR23423423456");

        accountRepository.save(account);

        Optional<Account> byAccountNo = accountRepository.findByAccountNo(account.getAccountNo());

        assertTrue(byAccountNo.isPresent());
        assertEquals(account.getAccountNo(), byAccountNo.get().getAccountNo());
        assertEquals(account.getIban(), byAccountNo.get().getIban());
        assertEquals(account.getAmount(), byAccountNo.get().getAmount());

        String accountString = objectMapper.writeValueAsString(byAccountNo.get());

        System.out.println(byAccountNo.get());
        System.out.println(accountString);

    }
}
