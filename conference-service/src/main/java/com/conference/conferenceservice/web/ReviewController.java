package com.conference.conferenceservice.web;

import com.conference.conferenceservice.dtos.ReviewDTO;
import com.conference.conferenceservice.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // Get all reviews
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // Get reviews by conference
    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByConference(@PathVariable UUID conferenceId) {
        return ResponseEntity.ok(reviewService.getReviewsByConference(conferenceId));
    }

    // Get single review
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.getReview(id));
    }

    // Create review for a conference
    @PostMapping("/conference/{conferenceId}")
    public ResponseEntity<ReviewDTO> createReview(
            @PathVariable UUID conferenceId,
            @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewService.createReview(conferenceId, reviewDTO));
    }

    // Update review
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(
            @PathVariable UUID id,
            @RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDTO));
    }

    // Delete review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}