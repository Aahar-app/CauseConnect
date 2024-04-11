package com.springboot.causeconnect.dto;
import java.util.Date;

import lombok.Data;

@Data
public class DonationRequestDto {

    private int DonationRequestId;
    private String ngoName;
    private String title;
    private String description;
    private Date pickupTime;



}
