package com.springboot.causeconnect.authentication.services;

import com.springboot.causeconnect.dto.JwtAuthResponse;
import com.springboot.causeconnect.dto.RefreshTokenRequest;
import com.springboot.causeconnect.dto.SignInRequest;

public interface AuthenticationService {

    public JwtAuthResponse signIn(SignInRequest signInRequest);

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
