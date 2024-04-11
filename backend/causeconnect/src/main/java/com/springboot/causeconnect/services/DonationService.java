package com.springboot.causeconnect.services;

import com.springboot.causeconnect.dto.DonationRequestDto;
import com.springboot.causeconnect.entities.DonationRequest;

public interface DonationService {

    public DonationRequest addDonationRequest(DonationRequestDto donationRequestDto);

    public int acceptDonationRequest(int donationRequestId);

    public int rejectDonationRequest(int donationRequestId);


}
