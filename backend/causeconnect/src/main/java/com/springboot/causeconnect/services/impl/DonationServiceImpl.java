package com.springboot.causeconnect.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.causeconnect.dao.DonationRequestRepository;
import com.springboot.causeconnect.dao.NgoRepository;
import com.springboot.causeconnect.dto.DonationRequestDto;
import com.springboot.causeconnect.entities.DonationRequest;
import com.springboot.causeconnect.services.DonationService;

import lombok.RequiredArgsConstructor;

import com.springboot.causeconnect.entities.Ngo;
import com.springboot.causeconnect.entities.Status;



@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService{


    @Autowired
    DonationRequestRepository donationRequestRepository;

    @Autowired
    NgoRepository ngoRepository;

    public DonationRequest addDonationRequest(DonationRequestDto donationRequestDto){

        DonationRequest donationRequest = new DonationRequest();


        // user id set karna hai vo divuu karegi
        //manual setup for now
        donationRequest.setUserId(0);


        String ngoName = donationRequestDto.getNgoName();
        donationRequest.setTitle(donationRequestDto.getTitle());
        donationRequest.setPickupTime(donationRequestDto.getPickupTime());
        donationRequest.setDescription(donationRequestDto.getDescription());
        donationRequest.setStatus(Status.SENT.toString());
        Ngo ngo = this.ngoRepository.findByNgoName(ngoName);
        donationRequest.setNgoId(ngo.getNgoId());


        return this.donationRequestRepository.save(donationRequest);
    }

    public int acceptDonationRequest(int donationRequestId){

        return this.donationRequestRepository.updateStatusWithQuery(donationRequestId, Status.ACCEPTED.toString());

        
    }

    public int rejectDonationRequest(int donationRequestId){

        return this.donationRequestRepository.updateStatusWithQuery(donationRequestId, Status.REJECTED.toString());

        
    }

}
