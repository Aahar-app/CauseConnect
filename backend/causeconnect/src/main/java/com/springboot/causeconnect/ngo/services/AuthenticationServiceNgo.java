package com.springboot.causeconnect.ngo.services;

import com.springboot.causeconnect.dto.JwtAuthResponse;
import com.springboot.causeconnect.dto.RefreshTokenRequest;
import com.springboot.causeconnect.ngo.dto.SignInRequestNgo;
import com.springboot.causeconnect.ngo.dto.SignUpRequestNgo;
import com.springboot.causeconnect.entities.Ngo;

public interface AuthenticationServiceNgo {

    public Ngo signup(SignUpRequestNgo signUpRequestNgo);

    public JwtAuthResponse signIn(SignInRequestNgo signInRequestNgo);

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
