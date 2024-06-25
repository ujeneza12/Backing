package com.rca.ac.rw.ne.backend.api.model;


import com.rca.ac.rw.ne.backend.model.dao.PastOrPresent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

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
    @PastOrPresent(message = "Banking date and time must be in the past or present")
    private Date banking_date_time;

    // Getters and Setters
}
