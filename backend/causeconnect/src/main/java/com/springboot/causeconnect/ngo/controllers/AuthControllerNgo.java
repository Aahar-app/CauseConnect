package com.springboot.causeconnect.ngo.controllers;

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


import com.springboot.causeconnect.ngo.dto.SignUpRequestNgo;

import com.springboot.causeconnect.entities.User;
import com.springboot.causeconnect.ngo.services.AuthenticationServiceNgo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth/ngo")
@RequiredArgsConstructor

public class AuthControllerNgo {

    @Autowired
    private AuthenticationServiceNgo authenticationServiceNgo;

    @CrossOrigin("*")
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignUpRequestNgo signUpRequestNgo,BindingResult bindingResult){
        User ngo =null;
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
           
            return ResponseEntity.badRequest().body(errors);
        }
        try{
             ngo = authenticationServiceNgo.signup(signUpRequestNgo);
             return ResponseEntity.ok(ngo);

        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    
}



