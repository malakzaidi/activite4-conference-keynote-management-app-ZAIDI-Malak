package com.conference.conferenceservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter
@Setter @Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private LocalDateTime date;
    private String texte;
    private int note;

    @ManyToOne
    @JoinColumn(name="conference_id")
    private Conference conference;
}
