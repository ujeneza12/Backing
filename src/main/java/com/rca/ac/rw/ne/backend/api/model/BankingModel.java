package com.rca.ac.rw.ne.backend.api.model;

import com.rca.ac.rw.ne.backend.model.LocalUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BankingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "user is required")
    private Long user_id;


    @NotNull
    @NotEmpty(message = "account is required")
    private String account;

    @NotNull
    private double amount;

    @NotNull
    @NotEmpty(message = "type is required")
    private String type; // "saving", "withdraw", "transfer"

    @NotNull
    private LocalDateTime banking_date_time;

    // Getters and Setters
}
