package com.conference.keynoteservice.dtos;

import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class KeynoteDTO {
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private String fonction;
}