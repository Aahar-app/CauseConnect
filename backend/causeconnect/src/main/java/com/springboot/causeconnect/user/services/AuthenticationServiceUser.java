package com.springboot.causeconnect.user.services;


import com.springboot.causeconnect.user.dto.SignUpRequestUser;
import com.springboot.causeconnect.entities.User;



public interface AuthenticationServiceUser {
    public User signup(SignUpRequestUser signUpRequest);

    
}
