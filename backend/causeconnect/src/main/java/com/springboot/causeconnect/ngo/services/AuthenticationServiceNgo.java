package com.springboot.causeconnect.ngo.services;

import com.springboot.causeconnect.ngo.dto.SignUpRequestNgo;
import com.springboot.causeconnect.entities.User;

public interface AuthenticationServiceNgo {

    public User signup(SignUpRequestNgo signUpRequestNgo);


}
