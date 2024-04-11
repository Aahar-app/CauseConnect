package com.springboot.causeconnect.ngo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/ngo")
@RequiredArgsConstructor
@CrossOrigin("*")
public class NgoController {

    @CrossOrigin("*")
    @PostMapping("/requestDonation")
    public ResponseEntity<String> requestDonation() {

        return ResponseEntity.ok("Request for donation has been sent to the admin");
    }
    



}
