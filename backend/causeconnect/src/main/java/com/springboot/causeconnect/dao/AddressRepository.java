package com.springboot.causeconnect.dao;

import com.springboot.causeconnect.entities.Address;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
