package com.conference.keynoteservice.dtos;

import java.util.UUID;

public record KeynoteDTO(
        UUID id ,String nom , String prenom, String email, String fonction) {

}
