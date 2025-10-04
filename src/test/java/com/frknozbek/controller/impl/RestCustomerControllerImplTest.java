package com.frknozbek.controller.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.MediaType;

import com.frknozbek.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frknozbek.dto.DtoCustomer;
import com.frknozbek.dto.DtoCustomerIU;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.jwt.*;

@WebMvcTest(RestCustomerControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
public class RestCustomerControllerImplTest {

    @MockitoBean
    private ICustomerService customerService;

    @MockitoBean
    private JWTService JWTService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSavCustomer() throws Exception {
        Calendar calendarIns = Calendar.getInstance();
        calendarIns.set(1999, 03, 30);

        DtoCustomerIU dtoCustomerIU = new DtoCustomerIU();
        dtoCustomerIU.setAccountId("12345");
        dtoCustomerIU.setAddressId("172356");
        dtoCustomerIU.setBirthOfDate(calendarIns.getTime());
        dtoCustomerIU.setFirstName("Furkan");
        dtoCustomerIU.setLastName("OZBEK");
        dtoCustomerIU.setTckn("543454353543");

        DtoCustomer dtoCustomer = new DtoCustomer();
        dtoCustomer.setId("1234");
        dtoCustomer.setCreateTime(new Date());
        dtoCustomer.setAccount(null);
        dtoCustomer.setAddress(null);
        dtoCustomer.setBirthOfDate(calendarIns.getTime());
        dtoCustomer.setFirstName("Furkan");
        dtoCustomer.setLastName("OZBEK");
        dtoCustomer.setTckn("543454353543");

        when(customerService.saveCustomer(any(DtoCustomerIU.class))).thenReturn(dtoCustomer);

        mockMvc.perform(post("/rest/api/customer/save")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(dtoCustomerIU)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo(200)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.id", equalTo("1234")));

    }

    @Test
    void testBaseException() throws Exception {
        Calendar calendarIns = Calendar.getInstance();
        calendarIns.set(1999, 03, 30);

        DtoCustomerIU dtoCustomerIU = new DtoCustomerIU();
        dtoCustomerIU.setAccountId("noRecord");
        dtoCustomerIU.setAddressId("noRecord");
        dtoCustomerIU.setBirthOfDate(calendarIns.getTime());
        dtoCustomerIU.setFirstName("Furkan");
        dtoCustomerIU.setLastName("OZBEK");
        dtoCustomerIU.setTckn("543454353543");

        DtoCustomer dtoCustomer = new DtoCustomer();
        dtoCustomer.setId("1234");
        dtoCustomer.setCreateTime(new Date());
        dtoCustomer.setAccount(null);
        dtoCustomer.setAddress(null);
        dtoCustomer.setBirthOfDate(calendarIns.getTime());
        dtoCustomer.setFirstName("Furkan");
        dtoCustomer.setLastName("OZBEK");
        dtoCustomer.setTckn("543454353543");

        when(customerService.saveCustomer(any(DtoCustomerIU.class)))
                .thenThrow(new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "noRecord")));

        mockMvc.perform(post("/rest/api/customer/save")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(dtoCustomerIU)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", equalTo(500)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.exception.path", equalTo("/rest/api/customer/save")));

    }

}
