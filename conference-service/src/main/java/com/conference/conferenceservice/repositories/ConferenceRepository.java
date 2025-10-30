package com.conference.conferenceservice.repositories;

import com.conference.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, UUID> {
    List<Conference> findByKeynoteId(UUID keynoteId);
}
