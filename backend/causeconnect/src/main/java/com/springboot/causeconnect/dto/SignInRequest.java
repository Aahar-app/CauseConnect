package com.springboot.causeconnect.dto;

import com.springboot.causeconnect.entities.Role;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;
    private String password;
    Role role;

}
