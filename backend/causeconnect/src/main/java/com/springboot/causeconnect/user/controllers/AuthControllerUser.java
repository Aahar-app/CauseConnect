package com.springboot.causeconnect.user.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.causeconnect.dto.RefreshTokenRequest;
import com.springboot.causeconnect.user.dto.SignInRequestUser;
import com.springboot.causeconnect.user.dto.SignUpRequestUser;
import com.springboot.causeconnect.entities.User;
import com.springboot.causeconnect.user.services.AuthenticationServiceUser;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor

public class AuthControllerUser {

    @Autowired
    private AuthenticationServiceUser authenticationServiceUser;

    @CrossOrigin("*")
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignUpRequestUser  signUpRequestUser,BindingResult bindingResult){
        User user =null;
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
           
            return ResponseEntity.badRequest().body(errors);
        }
        try{
             user = authenticationServiceUser.signup(signUpRequestUser);
             return ResponseEntity.ok(user);

        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
        @CrossOrigin("*")
       @PostMapping("/signin")
       public ResponseEntity<Object> signin(@RequestBody SignInRequestUser signInRequestUser){

        try{
            var jwtAuthResponse = authenticationServiceUser.signIn(signInRequestUser);
            return ResponseEntity.ok(jwtAuthResponse);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    @CrossOrigin("*")
     @PostMapping("/refresh")
       public ResponseEntity<Object> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){

        try{
            var jwtAuthResponse = authenticationServiceUser.refreshToken(refreshTokenRequest);
            return ResponseEntity.ok(jwtAuthResponse);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    
}



