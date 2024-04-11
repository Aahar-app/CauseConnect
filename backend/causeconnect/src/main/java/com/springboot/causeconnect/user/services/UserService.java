package com.springboot.causeconnect.user.services;


import org.springframework.security.core.userdetails.UserDetailsService;
import com.springboot.causeconnect.entities.User;


public interface UserService {
   

    public User addUser(User user);

    public User findByEmailAndPassword(String email, String password);
    

    public UserDetailsService userDetailsService();
}


