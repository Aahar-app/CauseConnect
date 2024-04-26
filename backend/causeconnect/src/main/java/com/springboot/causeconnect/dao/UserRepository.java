package com.springboot.causeconnect.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.springboot.causeconnect.entities.Role;
import com.springboot.causeconnect.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByEmailAndPassword(String email, String password);

    Optional<User> findByEmail(String username);

    User findByRole(Role role);
    
    User findByNgoName(String ngoName);

    

}
