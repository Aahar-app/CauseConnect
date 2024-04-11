package com.springboot.causeconnect.user.dto;

import lombok.Data;

@Data
public class SignInRequestUser {

    private String email;
    private String password;

}
