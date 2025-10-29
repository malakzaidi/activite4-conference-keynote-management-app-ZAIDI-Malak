package com.conference.conferenceservice.repositories;

import com.conference.conferenceservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findByConferenceId(UUID conferenceId);
}