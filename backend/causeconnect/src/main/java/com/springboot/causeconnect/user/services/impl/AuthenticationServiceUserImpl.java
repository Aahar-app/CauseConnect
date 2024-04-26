package com.springboot.causeconnect.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.causeconnect.dao.AddressRepository;
import com.springboot.causeconnect.dao.UserRepository;
import com.springboot.causeconnect.user.dto.SignUpRequestUser;
import com.springboot.causeconnect.entities.Address;
import com.springboot.causeconnect.entities.Role;
import com.springboot.causeconnect.entities.User;
import com.springboot.causeconnect.user.services.AuthenticationServiceUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceUserImpl implements AuthenticationServiceUser{

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationManager customAuthenticationManager;

    public User signup(SignUpRequestUser signUpRequestUser){
        
        
        User user = new User();
        user.setFirstName(signUpRequestUser.getFirstName());
        user.setLastName(signUpRequestUser.getLastName());
        user.setEmail(signUpRequestUser.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestUser.getPassword())); // Assuming password is hashed
        user.setRole(Role.USER);

        // Set address for the user
        Address address = signUpRequestUser.getAddress();
        address.setUser(user); // Set reference to user

        user.getAddressesUser().add(address);
        
        // Add address to user's list (optional)

        user = userRepository.save(user);

        this.addressRepository.save(address);
        return user;

        
    }


    


    


}
