package com.springboot.causeconnect.ngo.dto;

import com.springboot.causeconnect.entities.Address;

import lombok.Data;

@Data
public class SignUpRequestNgo {

     private String ngoName;

    private String ngoEmail;

    private String ngoPassword;

    private String ngoPhone;
 
    private String ngoWebsite;

    private String ngoDescription;
     
    private Address address;



}
