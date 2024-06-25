package com.rca.ac.rw.ne.backend.model.dao;

import com.rca.ac.rw.ne.backend.model.Banking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankingDAO extends JpaRepository<Banking, Long> {
}

