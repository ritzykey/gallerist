package com.frknozbek.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.frknozbek.model.Account;

@DataMongoTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testSaveAccount() {
        Account savedAccount = accountRepository.save(Account.builder().accountNo("1231412").build());

        assertNotNull(savedAccount);
        assertNotNull(savedAccount.getAccountNo());
    }
}
