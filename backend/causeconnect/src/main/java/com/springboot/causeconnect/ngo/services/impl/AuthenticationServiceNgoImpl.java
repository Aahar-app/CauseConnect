package com.springboot.causeconnect.ngo.services.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.springboot.causeconnect.dao.AddressRepository;
import com.springboot.causeconnect.dao.NgoRepository;
import com.springboot.causeconnect.dto.JwtAuthResponse;
import com.springboot.causeconnect.dto.RefreshTokenRequest;
import com.springboot.causeconnect.ngo.dto.SignInRequestNgo;
import com.springboot.causeconnect.ngo.dto.SignUpRequestNgo;
import com.springboot.causeconnect.entities.Address;
import com.springboot.causeconnect.entities.Ngo;
import com.springboot.causeconnect.entities.Role;
import com.springboot.causeconnect.ngo.services.AuthenticationServiceNgo;
import com.springboot.causeconnect.services.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceNgoImpl implements AuthenticationServiceNgo{

    
    @Autowired
    private  NgoRepository ngoRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final JWTService jwtService;
    public Ngo signup(SignUpRequestNgo signUpRequestNgo){
        
        
        Ngo ngo = new Ngo();
        ngo.setNgoName(signUpRequestNgo.getNgoName());
        ngo.setNgoEmail(signUpRequestNgo.getNgoEmail());
        ngo.setNgoPassword(passwordEncoder.encode(signUpRequestNgo.getNgoPassword())); // Assuming password is hashed
        ngo.setNgoPhone(signUpRequestNgo.getNgoPhone());
        ngo.setNgoWebsite(signUpRequestNgo.getNgoWebsite());
        ngo.setNgoDescription(signUpRequestNgo.getNgoDescription());
        ngo.setNgoBanner(signUpRequestNgo.getNgoBanner());
        ngo.setNgoLogo(signUpRequestNgo.getNgoLogo());
        ngo.setNgoType(signUpRequestNgo.getNgoType());
        ngo.setNgoCategory(signUpRequestNgo.getNgoCategory());
        ngo.setNgoSubCategory(signUpRequestNgo.getNgoSubCategory());
        ngo.setNgoRegistrationNumber(signUpRequestNgo.getNgoRegistrationNumber());
        ngo.setNgoRegistrationProof(signUpRequestNgo.getNgoRegistrationProof());
        ngo.setNgoPanNumber(signUpRequestNgo.getNgoPanNumber());
        ngo.setNgoPanProof(signUpRequestNgo.getNgoPanProof());
        ngo.setNgoGstNumber(signUpRequestNgo.getNgoGstNumber());
        ngo.setNgoGstProof(signUpRequestNgo.getNgoGstProof());
        ngo.setNgoBankName(signUpRequestNgo.getNgoBankName());
        ngo.setNgoBankBranch(signUpRequestNgo.getNgoBankBranch());
        ngo.setNgoBankAccountNumber(signUpRequestNgo.getNgoBankAccountNumber());
        ngo.setNgoBankIfscCode(signUpRequestNgo.getNgoBankIfscCode());
        ngo.setNgoBankProof(signUpRequestNgo.getNgoBankProof());
        ngo.setRole(Role.NGO);

        // Set address for the user
        Address address = signUpRequestNgo.getAddress();
        address.setNgo(ngo); // Set reference to user

        ngo.getAddresses().add(address);
        
        // Add address to user's list (optional)

        ngo = ngoRepository.save(ngo);

        this.addressRepository.save(address);
        return ngo;

        
    }

    public JwtAuthResponse signIn(SignInRequestNgo signInRequestNgo){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestNgo.getEmail(),signInRequestNgo.getPassword()));

        Ngo ngo  = this.ngoRepository.findByNgoEmail(signInRequestNgo.getEmail()).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
        var jwtToken = jwtService.generateToken(ngo);

        var refreshjwtToken = jwtService.generateRefreshToken(new HashMap<>(),ngo);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshtoken(refreshjwtToken);

        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest){

        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        Ngo ngo = ngoRepository.findByNgoEmail(userEmail).orElseThrow(()->new IllegalArgumentException("User not found"));

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), ngo)){
            var jwtToken = jwtService.generateToken(ngo);

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        jwtAuthResponse.setToken(jwtToken);
        jwtAuthResponse.setRefreshtoken(refreshTokenRequest.getToken());

        return jwtAuthResponse;
        }

        return null;

    }


    


}
