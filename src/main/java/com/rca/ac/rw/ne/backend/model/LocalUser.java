package com.rca.ac.rw.ne.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rca.ac.rw.ne.backend.model.dao.AllowedEmailDomains;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="local_user")
public class LocalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String dob;

    @NotNull
    private String account;

    @NotNull
    private double balance;

    @NotNull
    private Date lastUpdateTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Banking> transactions;



}
