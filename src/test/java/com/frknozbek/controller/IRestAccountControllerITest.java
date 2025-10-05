package com.frknozbek.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.frknozbek.dto.DtoAccount;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.repository.AccountRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class IRestAccountControllerITest {

    @Autowired
    IRestAccountController restAccountController;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testAccountNoNotFound() {
        
        String accountNo = UUID.randomUUID().toString();

        assertThrows(BaseException.class,
                () -> {
                    restAccountController.getAccountById(accountNo);
                });

        assertThrowsExactly(BaseException.class,
                () -> {
                    restAccountController.getAccountById(accountNo);
                }, new ErrorMessage(MessageType.NO_RECORD_EXIST, accountNo).prepareErrorMessage());

    }

    @Test
    void testAccountList() {

        RootEntity<List<DtoAccount>> listDtoAccount = restAccountController.listAccount();

        assertEquals(5, listDtoAccount.getPayload().size());
    }

}
