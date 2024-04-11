package com.springboot.causeconnect.dto;

import lombok.Data;

@Data
public class JwtAuthResponse {

    private String token;
    private String refreshtoken;

}
