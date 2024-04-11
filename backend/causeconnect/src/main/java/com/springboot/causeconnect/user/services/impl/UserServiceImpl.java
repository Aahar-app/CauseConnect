package com.springboot.causeconnect.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.causeconnect.dao.UserRepository;
import com.springboot.causeconnect.entities.User;
import com.springboot.causeconnect.user.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService{
     @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return this.userRepository.save(user);
    }

    public User findByEmailAndPassword(String email, String password){
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    public UserDetailsService userDetailsService(){

        return  new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) {

                return userRepository.findByEmail(username)
                            .orElseThrow(()-> new UsernameNotFoundException("User not found with email" + username));
                
            }
            
        };
    }

    

}
