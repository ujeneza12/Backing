package com.rca.ac.rw.ne.backend.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;


@Getter
@Setter
public class MessageEvent extends ApplicationEvent {

    private final LocalUser user;
    private final String message;
    private final LocalDateTime dateTime;

    public MessageEvent(Object source, LocalUser user, String message, LocalDateTime dateTime) {
        super(source);
        this.user = user;
        this.message = message;
        this.dateTime = dateTime;
    }


}
