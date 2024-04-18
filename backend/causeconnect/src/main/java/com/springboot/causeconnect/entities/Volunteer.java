package com.springboot.causeconnect.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int volunteerId;

    private String volunteerName;
    private String volunteerEmail;
    private String volunteerPassword;
    private String volunteerPhone;
    

}
