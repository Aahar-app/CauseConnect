package com.springboot.causeconnect.ngo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.springboot.causeconnect.dao.AddressRepository;
import com.springboot.causeconnect.dao.UserRepository;
import com.springboot.causeconnect.ngo.dto.SignUpRequestNgo;
import com.springboot.causeconnect.entities.Address;
import com.springboot.causeconnect.entities.Role;
import com.springboot.causeconnect.entities.User;
import com.springboot.causeconnect.ngo.services.AuthenticationServiceNgo;
import com.springboot.causeconnect.services.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceNgoImpl implements AuthenticationServiceNgo{

    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    public User signup(SignUpRequestNgo signUpRequestNgo){
        
        
        User ngo  = new User();
        ngo.setNgoName(signUpRequestNgo.getNgoName());
        ngo.setEmail(signUpRequestNgo.getNgoEmail());
        ngo.setPassword(passwordEncoder.encode(signUpRequestNgo.getNgoPassword())); // Assuming password is hashed
        ngo.setNgoPhone(signUpRequestNgo.getNgoPhone());
        ngo.setNgoWebsite(signUpRequestNgo.getNgoWebsite());
        ngo.setNgoDescription(signUpRequestNgo.getNgoDescription());
        
        ngo.setRole(Role.NGO);

        // // Set address for the user
        Address address = signUpRequestNgo.getAddress();
        address.setUser(ngo); // Set reference to user

        ngo.getAddressesUser().add(address);
        
        // // Add address to user's list (optional)

        
        ngo = userRepository.save(ngo);

         this.addressRepository.save(address);
        return ngo;

        
    }

}
