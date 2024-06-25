package com.rca.ac.rw.ne.backend.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime bankingDateTime;

    // Getters and Setters
}
