package com.conference.conferenceservice.entities;

import com.conference.conferenceservice.enums.TypeConference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @NoArgsConstructor
@AllArgsConstructor @Builder @Getter
@Setter
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String titre;
    @Enumerated(EnumType.STRING)
    private TypeConference type;
    private LocalDate date;
    private int dureeMinutes;
    private int nombreInscrits;
    private double score;
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
