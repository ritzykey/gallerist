package com.frknozbek.controller.impl;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frknozbek.dto.AuthRequest;
import com.frknozbek.dto.AuthResponse;
import com.frknozbek.service.IAuthenticationService;

@SpringBootTest
@AutoConfigureMockMvc
public class RestAuthenticationControllerImplTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private IAuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAuthenticate() throws Exception {
        AuthRequest authRequest = new AuthRequest();

        authRequest.setUsername("fozbek02");
        authRequest.setPassword("1");

        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("accessToken");
        authResponse.setRefreshToken("refreshToken");

        when(authenticationService.authenticate(ArgumentMatchers.any(AuthRequest.class))).thenReturn(authResponse);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/authenticate")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.accessToken").value("accessToken"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.payload.refreshToken").value("refreshToken"));

    }

    @Test
    void testRefreshToken() {
        
    }

    @Test
    void testRegister() {

    }
}
