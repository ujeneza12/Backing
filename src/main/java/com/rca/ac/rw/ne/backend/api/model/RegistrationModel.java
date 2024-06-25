package com.rca.ac.rw.ne.backend.api.model;


import com.rca.ac.rw.ne.backend.model.dao.AllowedEmailDomains;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistrationModel {

    @NotNull
    @NotEmpty(message = "firstname is required")
    private String firstname;

    @NotNull
    @NotEmpty(message = "lastname is required")
    private String lastname;

    @NotNull
    @Email
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    @AllowedEmailDomains(domains = {"gmail.com", "outlook.com","yahoo.com"})
    private String email;

    @NotNull
    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @NotNull
    @NotEmpty(message = "dob is required")
    private String dob;

    @NotNull
    @NotEmpty(message = "account is required")
    private String account;

    @NotNull
    private double balance;


    @NotNull
    private Date last_update_time;

}
