package com.springboot.causeconnect.authentication.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.causeconnect.authentication.services.AuthenticationService;
import com.springboot.causeconnect.dto.SignInRequest;


@RestController
@RequestMapping("/api/auth")

public class AuthController {


    @Autowired
    AuthenticationService authenticationService;

    @CrossOrigin("*")
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody SignInRequest signInRequest) {

        try {
            var jwtAuthResponse = authenticationService.signIn(signInRequest);
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}

    

  

