package com.rca.ac.rw.ne.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

    private String message;

    private LocalDateTime dateTime;

    // Getters and setters
    // Constructors, if needed

    public Message() {
    }

    public Message(LocalUser user, String message, LocalDateTime dateTime) {
        this.user = user;
        this.message = message;
        this.dateTime = dateTime;
    }

    // Getters and setters
}
