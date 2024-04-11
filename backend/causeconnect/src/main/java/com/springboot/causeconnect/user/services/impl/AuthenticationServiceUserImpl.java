package com.springboot.causeconnect.user.services.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.causeconnect.dao.AddressRepository;
import com.springboot.causeconnect.dao.UserRepository;
import com.springboot.causeconnect.dto.JwtAuthResponse;
import com.springboot.causeconnect.dto.RefreshTokenRequest;
import com.springboot.causeconnect.user.dto.SignInRequestUser;
import com.springboot.causeconnect.user.dto.SignUpRequestUser;
import com.springboot.causeconnect.entities.Address;
import com.springboot.causeconnect.entities.Role;
import com.springboot.causeconnect.entities.User;
import com.springboot.causeconnect.user.services.AuthenticationServiceUser;
import com.springboot.causeconnect.services.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceUserImpl implements AuthenticationServiceUser{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final JWTService jwtService;
    public User signup(SignUpRequestUser signUpRequestUser){
        
        
        User user = new User();
        user.setFirstName(signUpRequestUser.getFirstName());
        user.setLastName(signUpRequestUser.getLastName());
        user.setEmail(signUpRequestUser.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestUser.getPassword())); // Assuming password is hashed
        user.setRole(Role.USER);

        // Set address for the user
        Address address = signUpRequestUser.getAddress();
        address.setUser(user); // Set reference to user

        user.getAddresses().add(address);
        
        // Add address to user's list (optional)

        user = userRepository.save(user);

        this.addressRepository.save(address);
        return user;

        
    }

    public JwtAuthResponse signIn(SignInRequestUser signInRequestUser){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestUser.getEmail(),signInRequestUser.getPassword()));

        User user  = this.userRepository.findByEmail(signInRequestUser.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(user);

        var refreshjwtToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshtoken(refreshjwtToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){

        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new IllegalArgumentException("User not found"));

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwtToken = jwtService.generateToken(user);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshtoken(refreshTokenRequest.getToken());

        return jwtAuthResponse;
        }

        return null;

    }


    


}
