package com.rca.ac.rw.ne.backend.model.dao;

import com.rca.ac.rw.ne.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDAO extends JpaRepository<Message, Long> {
    // You can add custom query methods if needed
}
