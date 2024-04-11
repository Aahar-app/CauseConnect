package com.springboot.causeconnect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.causeconnect.entities.DonationRequest;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, Integer>{

    DonationRequest findByRequestId(int donationRequestId);

    @Modifying
    @Query("UPDATE DonationRequest d SET d.status = :status WHERE d.requestId = :donationRequestId")
    int updateStatusWithQuery(int donationRequestId, String status);

    
}
