package com.rca.ac.rw.ne.backend.model;


import com.rca.ac.rw.ne.backend.model.dao.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageListener {

    private final MessageDAO messageDAO;

    @Autowired
    public MessageListener(MessageDAO messageDAO) {
        this.messageDAO= messageDAO;
    }

    @EventListener
    public void handleMessageEvent(MessageEvent event) {
        LocalUser user = event.getUser();
        String messageText = event.getMessage();
        LocalDateTime dateTime = event.getDateTime();

        // Save the message to the database
        Message message = new Message(user, messageText, dateTime);
        messageDAO.save(message);
    }
}
