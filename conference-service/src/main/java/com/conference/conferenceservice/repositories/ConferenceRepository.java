package com.conference.conferenceservice.repositories;

import com.conference.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConferenceRepository extends JpaRepository<Conference, UUID> {
}
