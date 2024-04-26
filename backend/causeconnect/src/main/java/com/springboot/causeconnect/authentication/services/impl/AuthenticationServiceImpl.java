package com.springboot.causeconnect.authentication.services.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.causeconnect.authentication.services.AuthenticationService;
import com.springboot.causeconnect.dao.AddressRepository;
import com.springboot.causeconnect.dao.UserRepository;
import com.springboot.causeconnect.dto.JwtAuthResponse;
import com.springboot.causeconnect.dto.RefreshTokenRequest;
import com.springboot.causeconnect.dto.SignInRequest;
import com.springboot.causeconnect.entities.User;
import com.springboot.causeconnect.services.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public JwtAuthResponse signIn(SignInRequest signInRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        User user = this.userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);

        var refreshjwtToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshtoken(refreshjwtToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwtToken = jwtService.generateToken(user);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

            jwtAuthResponse.setToken(jwtToken);
            jwtAuthResponse.setRefreshtoken(refreshTokenRequest.getToken());

            return jwtAuthResponse;
        }

        return null;

    }

}
