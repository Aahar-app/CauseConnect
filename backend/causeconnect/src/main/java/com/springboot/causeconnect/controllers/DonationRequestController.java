package com.springboot.causeconnect.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.causeconnect.dto.DonationRequestDto;
import com.springboot.causeconnect.entities.DonationRequest;
import com.springboot.causeconnect.services.DonationService;

import jakarta.validation.Valid;
import java.util.List;

@RequestMapping("/api/donationRequest")
public class DonationRequestController {

    //post the donation - user

    @Autowired
    DonationService donationService;

    @PostMapping("/user/donate")
    public ResponseEntity<Object> donate(@Valid @RequestBody DonationRequestDto donationRequestDto , BindingResult bindingResult) {

        DonationRequest donationRequest =null;
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
           
            return ResponseEntity.badRequest().body(errors);
        }
        try{
             donationRequest = donationService.addDonationRequest(donationRequestDto);
             return ResponseEntity.ok(donationRequest);

        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    

    //accept donation - ngo

    @PostMapping("/ngo/accept")
    public ResponseEntity<Object>acceptDonation(@RequestBody DonationRequestDto donationRequestDto){

    

    int donationId = donationRequestDto.getDonationRequestId();

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

    @PostMapping("/ngo/reject")
    public ResponseEntity<Object>rejectDonation(@RequestBody DonationRequestDto donationRequestDto){

    

    int donationId = donationRequestDto.getDonationRequestId();

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
