package com.frknozbek.service;

import com.frknozbek.dto.AuthRequest;
import com.frknozbek.dto.AuthResponse;
import com.frknozbek.dto.DtoUser;
import com.frknozbek.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);

}