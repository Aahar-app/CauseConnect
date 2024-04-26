package com.springboot.causeconnect.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.causeconnect.dao.DonationRequestRepository;
import com.springboot.causeconnect.dto.DonationRequestDto;
import com.springboot.causeconnect.entities.DonationRequest;
import com.springboot.causeconnect.services.DonationService;

import lombok.RequiredArgsConstructor;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.springboot.causeconnect.entities.Role;
import com.springboot.causeconnect.entities.Status;



@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService{


    @Autowired
    DonationRequestRepository donationRequestRepository;



    public DonationRequest addDonationRequest(DonationRequestDto donationRequestDto){

        DonationRequest donationRequest = new DonationRequest();


        // user id set karna hai vo divuu karegi
        //manual setup for now
        donationRequest.setUserId(donationRequestDto.getUserId());


       // String ngoName = donationRequestDto.getNgoName();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(date);

        donationRequest.setRequestTime(formattedDate);
        donationRequest.setTitle(donationRequestDto.getTitle());
        donationRequest.setPickupTime(donationRequestDto.getPickupTime());
        donationRequest.setDescription(donationRequestDto.getDescription());
        donationRequest.setStatus(Status.SENT.toString());
      //  Ngo ngo = this.ngoRepository.findByNgoName(ngoName);
        donationRequest.setNgoId(donationRequestDto.getNgoId());
        


        return this.donationRequestRepository.save(donationRequest);
    }

    public int acceptDonationRequest(int donationRequestId){

        return this.donationRequestRepository.updateStatusWithQuery(donationRequestId, Status.ACCEPTED.toString());

        
    }

    public int rejectDonationRequest(int donationRequestId){

        return this.donationRequestRepository.updateStatusWithQuery(donationRequestId, Status.REJECTED.toString());

        
    }

}
