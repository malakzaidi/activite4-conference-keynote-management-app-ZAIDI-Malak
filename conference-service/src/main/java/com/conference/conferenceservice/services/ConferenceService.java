package com.conference.conferenceservice.services;

import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.entities.Conference;
import com.conference.conferenceservice.mappers.ConferenceMapper;
import com.conference.conferenceservice.repositories.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ConferenceService {

    private final ConferenceRepository repository;
    private final ConferenceMapper mapper;

    // Création
    public ConferenceDTO createConference(ConferenceDTO dto) {
        Conference entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    // Mise à jour
    public ConferenceDTO updateConference(UUID id, ConferenceDTO dto) {
        Conference existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
        existing.setTitre(dto.getTitre());
        existing.setType(dto.getType());
        existing.setDate(dto.getDate());
        existing.setDureeMinutes(dto.getDureeMinutes());
        existing.setNombreInscrits(dto.getNombreInscrits());
        existing.setScore(dto.getScore());
        existing.setReviews(dto.getReviews());
        return mapper.toDto(repository.save(existing));
    }

    // Suppression
    public void deleteConference(UUID id) {
        repository.deleteById(id);
    }

    // Lecture d'une conférence
    public ConferenceDTO getConference(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
    }

    // Liste de toutes les conférences
    public List<ConferenceDTO> getAllConferences() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
