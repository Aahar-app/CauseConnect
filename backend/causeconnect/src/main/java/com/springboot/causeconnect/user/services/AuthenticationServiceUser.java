package com.springboot.causeconnect.user.services;



import com.springboot.causeconnect.dto.JwtAuthResponse;
import com.springboot.causeconnect.dto.RefreshTokenRequest;
import com.springboot.causeconnect.user.dto.SignInRequestUser;
import com.springboot.causeconnect.user.dto.SignUpRequestUser;
import com.springboot.causeconnect.entities.User;



public interface AuthenticationServiceUser {
    public User signup(SignUpRequestUser signUpRequest);

    public JwtAuthResponse signIn(SignInRequestUser signInRequest);

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
