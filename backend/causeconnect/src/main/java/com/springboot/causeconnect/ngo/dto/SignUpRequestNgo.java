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

    private String ngoLogo;

    private String ngoBanner;

    private String ngoType;

    private String ngoCategory;

    private String ngoSubCategory;

    private String ngoRegistrationNumber;

    private String ngoRegistrationProof;

    private String ngoPanNumber;

    private String ngoPanProof;

    private String ngoGstNumber;

    private String ngoGstProof;

    private String ngoBankName;

    private String ngoBankBranch;

    private String ngoBankAccountNumber;

    private String ngoBankIfscCode;

    private String ngoBankProof;

     private Address address;



}
