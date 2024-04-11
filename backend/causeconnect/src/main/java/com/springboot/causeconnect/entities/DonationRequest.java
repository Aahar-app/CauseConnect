package com.springboot.causeconnect.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.Date;

@Data
@Entity
@Table(name = "donation_request")
public class DonationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int requestId;

    @NotNull
    private int userId;

    @NotNull
    private int ngoId;


    @NotNull
    private String title;

    private String description;

    @NotNull
    private Date requestTime;

    @NotNull
    private Date pickupTime;

    @NotNull
    private Date deliveryTime;

    private String status;

    // @ManyToOne
    // private User user;

    //  @ManyToOne
    // private Ngo ngo;


    

}
