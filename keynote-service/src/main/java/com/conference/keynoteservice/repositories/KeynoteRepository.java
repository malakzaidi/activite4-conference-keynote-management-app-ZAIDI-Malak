package com.conference.keynoteservice.repositories;

import com.conference.keynoteservice.entities.Keynote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface KeynoteRepository extends JpaRepository<Keynote, UUID> {
    Optional<Keynote> findByEmail(String email);
}
