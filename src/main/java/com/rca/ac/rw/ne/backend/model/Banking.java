package com.rca.ac.rw.ne.backend.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class Banking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

    private String account;
    private double amount;
    private String type; // "saving", "withdraw", "transfer"
    private Date banking_date_time;

    // Getters and Setters
}
