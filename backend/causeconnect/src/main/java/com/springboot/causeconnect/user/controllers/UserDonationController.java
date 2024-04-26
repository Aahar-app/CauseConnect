package com.springboot.causeconnect.user.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.causeconnect.dto.DonationRequestDto;
import com.springboot.causeconnect.entities.DonationRequest;
import com.springboot.causeconnect.services.DonationService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserDonationController {

    //post the donation - user

    @Autowired
    DonationService donationService;

    @PostMapping("/donate")
    public ResponseEntity<Object> donate( @RequestBody DonationRequestDto donationRequestDto) {

        DonationRequest donationRequest =null;
        // if(bindingResult.hasErrors()){
        //     List<ObjectError> errors = bindingResult.getAllErrors();
           
        //     return ResponseEntity.badRequest().body(errors);
        // }
        try{
             donationRequest = donationService.addDonationRequest(donationRequestDto);
             return ResponseEntity.ok(donationRequest);

        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
