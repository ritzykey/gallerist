package com.frknozbek.controller.impl;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frknozbek.dto.DtoAccount;
import com.frknozbek.dto.DtoAccountIU;
import com.frknozbek.enums.CurrencyType;
import com.frknozbek.jwt.JWTService;
import com.frknozbek.service.IAccountService;

@WebMvcTest(RestAccountControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
public class RestAccountControllerImplTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private IAccountService accountService;

    @MockitoBean
    private JWTService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveAccount() throws Exception {
        DtoAccountIU dtoAccountIU = new DtoAccountIU();
        dtoAccountIU.setAccountNo("123123");
        dtoAccountIU.setAmount(new BigDecimal(12312));
        dtoAccountIU.setCurrencyType(CurrencyType.TL);
        dtoAccountIU.setIban("TR7567453");

        DtoAccount dtoAccount = new DtoAccount();
        dtoAccount.setId("1412321");
        dtoAccount.setCreateTime(new Date());
        dtoAccount.setAccountNo("123123");
        dtoAccount.setAmount(new BigDecimal(12312));
        dtoAccount.setCurrencyType(CurrencyType.TL);
        dtoAccount.setIban("TR7567453");

        when(accountService.saveAccount(ArgumentMatchers.any(DtoAccountIU.class))).thenReturn(dtoAccount);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/rest/api/account/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dtoAccountIU)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.accountNo").value("123123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.amount").value("12312"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.currencyType").value("TL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.iban").value("TR7567453"));

    }
}
