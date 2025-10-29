package com.conference.conferenceservice.services;

import com.conference.conferenceservice.dtos.ReviewDTO;
import com.conference.conferenceservice.entities.Conference;
import com.conference.conferenceservice.entities.Review;
import com.conference.conferenceservice.mappers.ReviewMapper;
import com.conference.conferenceservice.repositories.ConferenceRepository;
import com.conference.conferenceservice.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ConferenceRepository conferenceRepository;
    private final ReviewMapper mapper;

    // Create a review for a conference
    public ReviewDTO createReview(UUID conferenceId, ReviewDTO dto) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        Review review = mapper.toEntity(dto);
        review.setId(null); // Let JPA generate ID
        review.setDate(LocalDateTime.now());
        review.setConference(conference);

        Review saved = reviewRepository.save(review);
        return mapper.toDto(saved);
    }

    // Get all reviews for a conference
    public List<ReviewDTO> getReviewsByConference(UUID conferenceId) {
        return reviewRepository.findByConferenceId(conferenceId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // Get a single review
    public ReviewDTO getReview(UUID id) {
        return reviewRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    // Update a review
    public ReviewDTO updateReview(UUID id, ReviewDTO dto) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        existing.setTexte(dto.getTexte());
        existing.setNote(dto.getNote());

        return mapper.toDto(reviewRepository.save(existing));
    }

    // Delete a review
    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }

    // Get all reviews
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}