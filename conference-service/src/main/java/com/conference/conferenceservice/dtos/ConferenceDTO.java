package com.conference.conferenceservice.dtos;

import com.conference.conferenceservice.entities.Review;
import com.conference.conferenceservice.enums.TypeConference;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ConferenceDTO {
    private UUID id;
    private String titre;
    private TypeConference type;
    private LocalDate date;
    private int dureeMinutes;
    private int nombreInscrits;
    private double score;
    private List<Review> reviews;
}
