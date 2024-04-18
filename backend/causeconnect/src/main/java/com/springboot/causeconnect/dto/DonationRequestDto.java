package com.springboot.causeconnect.dto;
import java.util.Date;

import lombok.Data;

@Data
public class DonationRequestDto {

    private int userId;
    private int ngoId;
    private String title;
    private String description;
    private String pickupTime;



}
