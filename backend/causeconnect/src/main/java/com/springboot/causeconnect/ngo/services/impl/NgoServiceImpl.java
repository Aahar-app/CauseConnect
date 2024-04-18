package com.springboot.causeconnect.ngo.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.causeconnect.dao.NgoRepository;
import com.springboot.causeconnect.entities.Ngo;
import com.springboot.causeconnect.ngo.services.NgoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NgoServiceImpl implements NgoService{

    private NgoRepository ngoRepository;

    public Ngo addNgo(Ngo ngo){
        return this.ngoRepository.save(ngo);
    }

     public UserDetailsService ngoDetailsService(){

        return  new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) {

                return ngoRepository.findByNgoEmail(username)
                            .orElseThrow(()-> new UsernameNotFoundException("User not found with email" + username));
                
            }
            
        };
    }
}
