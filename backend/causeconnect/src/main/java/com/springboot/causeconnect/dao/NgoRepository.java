// package com.springboot.causeconnect.dao;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import org.springframework.stereotype.Repository;

// import com.springboot.causeconnect.entities.Role;
// import com.springboot.causeconnect.entities.Ngo;

// @Repository
// public interface NgoRepository extends JpaRepository<Ngo, Integer>{

//     public Ngo findByNgoEmailAndNgoPassword(String ngoEmail, String ngoPassword);

//     Optional<Ngo> findByNgoEmail(String username);

//     Ngo findByNgoName(String ngoName);

//     Ngo findByRole(Role role);

// }
