package com.frknozbek.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.frknozbek.dto.AuthRequest;
import com.frknozbek.dto.RefreshTokenRequest;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.model.RefreshToken;
import com.frknozbek.repository.RefreshTokenRepository;
import com.frknozbek.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class IRestAuthenticationControllerITest {

    @Autowired
    IRestAuthenticationController restAuthenticationController;

    @MockitoBean
    RefreshTokenRepository refreshTokenRepository;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    void testInvalidUserAuthenticate() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("Ayhan");
        authRequest.setPassword("123456");

        assertThrows(BaseException.class, () -> {

            restAuthenticationController.authenticate(authRequest);
        });

    }

    @Test
    void testRefreshToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("null");

        assertThrowsExactly(BaseException.class, () -> {

            restAuthenticationController.refreshToken(refreshTokenRequest);

        }, new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshTokenRequest.getRefreshToken())
                .prepareErrorMessage());
    }

    @Test
    void testRefreshExpiredToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        refreshTokenRequest.setRefreshToken("null");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, 03, 30);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpiredDate(calendar.getTime());

        when(refreshTokenRepository.findByRefreshToken(any())).thenReturn(Optional.ofNullable(refreshToken));

        assertThrowsExactly(BaseException.class, () -> {

            restAuthenticationController.refreshToken(refreshTokenRequest);

        }, new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, refreshTokenRequest.getRefreshToken())
                .prepareErrorMessage());
    }

    @Test
    void testRegister() {

    }
}
