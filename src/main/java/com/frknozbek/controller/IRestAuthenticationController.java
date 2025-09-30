package com.frknozbek.controller;

import com.frknozbek.dto.AuthRequest;
import com.frknozbek.dto.AuthResponse;
import com.frknozbek.dto.DtoUser;
import com.frknozbek.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
