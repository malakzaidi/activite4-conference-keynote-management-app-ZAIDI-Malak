package com.conference.keynoteservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity @NoArgsConstructor @AllArgsConstructor @Builder
@Getter
@Setter
public class Keynote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private String fonction;
}
