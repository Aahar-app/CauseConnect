package com.springboot.causeconnect.entities;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "ngo")
public class Ngo implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ngoId")
    private int ngoId;

    @NotNull
     @Column(name = "ngoName")
    private String ngoName;

    @NotNull
    @Column(name = "ngoEmail")
    private String ngoEmail;

    @NotNull
    @Column(name = "ngoPassword")
    private String ngoPassword;

    @NotNull
    @Column(name = "addressId")
    private int addressId;

    

    @NotNull
    @Column(name = "ngoPhone")
    private String ngoPhone;

    @NotNull
    @Column(name = "ngoWebsite")
    private String ngoWebsite;

    @NotNull
    @Column(name = "ngoDescription")
    private String ngoDescription;

    @NotNull
    @Column(name = "ngoLogo")
    private String ngoLogo;

    @NotNull
    @Column(name = "ngoBanner")
    private String ngoBanner;


    @NotNull
    @Column(name = "ngoType")
    private String ngoType;

    @Column(name = "ngoCategory")
    private String ngoCategory;
    
    @Column(name = "ngoSubCategory")
    private String ngoSubCategory;

    @NotNull
    @Column(name = "ngoRegistrationNumber")
    private String ngoRegistrationNumber;

    @NotNull
    @Column(name = "ngoRegistrationProof")
    private String ngoRegistrationProof;

    @NotNull
    @Column(name = "ngoPanNumber")
    private String ngoPanNumber;

    @NotNull
    @Column(name = "ngoPanProof")
    private String ngoPanProof;

    @NotNull
    @Column(name = "ngoGstNumber")
    private String ngoGstNumber;

    @NotNull
    @Column(name = "ngoGstProof")
    private String ngoGstProof;

    @NotNull
    @Column(name = "ngoBankName")
    private String ngoBankName;

    @NotNull
    @Column(name = "ngoBankBranch")
    private String ngoBankBranch;

    @NotNull
    @Column(name = "ngoBankAccountNumber")

    private String ngoBankAccountNumber;

    @NotNull
    @Column(name = "ngoBankIfscCode")
    private String ngoBankIfscCode;

    @NotNull
    @Column(name = "ngoBankProof")
    private String ngoBankProof;

     @Column(name="role")
    Role role;

    
    @OneToMany(mappedBy = "ngo") // mappedBy points to the field in Address referring to Ngo
    private List<Address> addresses = new ArrayList<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.ngoPassword;
    }

    @Override
    public String getUsername() {
        return this.ngoEmail;
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
