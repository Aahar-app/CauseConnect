package com.springboot.causeconnect.entities;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties({"password"})
@Entity
@Table(name="user")
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;

    
    private String firstName;

    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private int successfulDonation;

    // ngo details
    @Column(name = "ngoName")
    private String ngoName;
   
    @Column(name = "ngoPhone")
    private String ngoPhone;

    
    @Column(name = "ngoWebsite")
    private String ngoWebsite;

    
    @Column(name = "ngoDescription")
    private String ngoDescription;



    @Column(name="role")
    Role role;

   @OneToMany(mappedBy = "user" ) // mappedBy points to the field in Address referring to User
   @JsonManagedReference
    private List<Address> addressesUser = new ArrayList<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
        
    }
    @Override
    public String getUsername() {
        return this.email;
        
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
       return true;
    }

    
   


}
