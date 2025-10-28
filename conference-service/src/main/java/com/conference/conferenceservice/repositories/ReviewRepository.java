package com.conference.conferenceservice.repositories;

import com.conference.conferenceservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
