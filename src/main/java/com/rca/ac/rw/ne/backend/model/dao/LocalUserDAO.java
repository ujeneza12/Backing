package com.rca.ac.rw.ne.backend.model.dao;

import com.rca.ac.rw.ne.backend.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LocalUserDAO extends JpaRepository<LocalUser, Long> {
    Optional<LocalUser> findByAccount(String account);
}