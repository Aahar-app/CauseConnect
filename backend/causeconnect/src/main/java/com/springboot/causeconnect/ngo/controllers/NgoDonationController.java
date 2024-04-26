package com.springboot.causeconnect.ngo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.causeconnect.services.DonationService;

@RestController
@RequestMapping("api/ngo")
public class NgoDonationController {

    @Autowired
    private DonationService donationService;

    //accept donation - ngo

    @GetMapping("/accept/{donationId}")
    public ResponseEntity<Object>acceptDonation(@PathVariable("donationId") int donationId){

    

    
    // donationRequestDto.getDonationRequestId();

    try{
        int status = donationService.acceptDonationRequest(donationId);
        //no of rows affected
        if(status>0)
        return ResponseEntity.ok(status);
        else
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
        


    }

     @GetMapping("/ngo/reject/{donationId}")
    public ResponseEntity<Object>rejectDonation(@PathVariable("donationId") int donationId){

    //donationRequestDto.getDonationRequestId();

    try{
        int status = donationService.rejectDonationRequest(donationId);
        //no of rows affected
        if(status>0)
        return ResponseEntity.ok(status);
        else
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    catch(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
        


    }


}
