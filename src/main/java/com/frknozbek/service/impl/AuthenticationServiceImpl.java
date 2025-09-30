package com.frknozbek.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.frknozbek.dto.AuthRequest;
import com.frknozbek.dto.AuthResponse;
import com.frknozbek.dto.DtoUser;
import com.frknozbek.dto.RefreshTokenRequest;
import com.frknozbek.exception.BaseException;
import com.frknozbek.exception.ErrorMessage;
import com.frknozbek.exception.MessageType;
import com.frknozbek.jwt.JWTService;
import com.frknozbek.model.RefreshToken;
import com.frknozbek.model.User;
import com.frknozbek.repository.RefreshTokenRepository;
import com.frknozbek.repository.UserRepository;
import com.frknozbek.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JWTService jwtService;

    private User createUser(AuthRequest input) {
        User user = new User();

        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }

    private RefreshToken creaRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setCreateTime(new Date());

        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));

        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        refreshToken.setUser(user);

        return refreshToken;

    }

    @Override
    public DtoUser register(AuthRequest input) {

        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    input.getUsername(), input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(input.getUsername());

            String accessToken = jwtService.generateToken(optUser.get());

            RefreshToken savedRefreshToken = refreshTokenRepository.save(creaRefreshToken(optUser.get()));

            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }
    }

    public boolean isValidRefreshToken(Date expireDate) {
        return new Date().before(expireDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {
        Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
        optRefreshToken.orElseThrow(() -> new BaseException(
                new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken())));

        if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
            throw new BaseException(
                    new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));
        }

        User user = optRefreshToken.get().getUser();

        String accessToken = jwtService.generateToken(user);

        RefreshToken savedRefreshToken = refreshTokenRepository.save(creaRefreshToken(user));

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }

}
