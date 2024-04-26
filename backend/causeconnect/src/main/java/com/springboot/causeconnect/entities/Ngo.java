// package com.springboot.causeconnect.entities;

// import java.util.Collection;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.NotNull;
// import lombok.Data;
// import java.util.List;
// import java.util.ArrayList;

// @Data
// @JsonIgnoreProperties({"password", "ngoLogo", "ngoBanner"})
// @Entity
// @Table(name = "ngo")
// public class Ngo implements UserDetails{

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(name = "ngoId")
//     private int ngoId;

//     @NotNull
//     @Column(name = "ngoName")
//     private String ngoName;

//     @NotNull
//     @Column(name = "ngoEmail")
//     private String ngoEmail;

//     @NotNull
//     @Column(name = "ngoPassword")
//     private String ngoPassword;


//     @NotNull
//     @Column(name = "ngoPhone")
//     private String ngoPhone;

//     @NotNull
//     @Column(name = "ngoWebsite")
//     private String ngoWebsite;

//     @NotNull
//     @Column(name = "ngoDescription")
//     private String ngoDescription;

   

//      @Column(name="role")
//     Role role;

    
//     @OneToMany(mappedBy = "ngo") // mappedBy points to the field in Address referring to Ngo
//     //@JsonManagedReference
//     private List<Address> addressesNgo = new ArrayList<>();
    
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return List.of(new SimpleGrantedAuthority(role.name()));
//     }

//     @Override
//     public String getPassword() {
//         return this.ngoPassword;
//     }

//     @Override
//     public String getUsername() {
//         return this.ngoEmail;
//         }

//     @Override
//     public boolean isAccountNonExpired() {
//        return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//       return true;
//     }

//     @Override
//     public boolean isEnabled() {
//      return true;
//     }

    

// }
