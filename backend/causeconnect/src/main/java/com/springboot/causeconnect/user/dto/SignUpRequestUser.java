package com.springboot.causeconnect.user.dto;

import com.springboot.causeconnect.entities.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class SignUpRequestUser {

    @NotBlank(message = "first name cant be empty")
    private String firstName;
    @NotBlank(message = "last name cant be empty")
    private String lastName;
    @NotBlank(message = "email name cant be empty")
    @Email(message = "email is not valid")
    private String email;
    @Size(min = 3, max = 10, message = "password:password must be between 3 to 10 characters")
    @NotBlank(message = "password cant be empty")
    private String password;

    private Address address;

   


}
