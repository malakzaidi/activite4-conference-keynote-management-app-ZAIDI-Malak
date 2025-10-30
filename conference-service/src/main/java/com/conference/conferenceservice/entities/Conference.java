package com.conference.conferenceservice.entities;

import com.conference.conferenceservice.enums.TypeConference;
import com.conference.conferenceservice.models.KeynoteDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titre;

    @Enumerated(EnumType.STRING)
    private TypeConference type;

    private LocalDate date;

    private int dureeMinutes;

    private int nombreInscrits;

    private double score;

    // ID of the keynote Speaker
    private UUID keynoteId;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Transient
    private KeynoteDTO keynote; // Not persistent , filled by Feign
}