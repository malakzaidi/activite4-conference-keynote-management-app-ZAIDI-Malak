package com.conference.conferenceservice.services;

import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.entities.Conference;
import com.conference.conferenceservice.feign.KeynoteRestClient;
import com.conference.conferenceservice.mappers.ConferenceMapper;
import com.conference.conferenceservice.models.KeynoteDTO;
import com.conference.conferenceservice.repositories.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ConferenceService {

    private final ConferenceRepository repository;
    private final ConferenceMapper mapper;
    private final KeynoteRestClient keynoteRestClient;

    public ConferenceDTO createConference(ConferenceDTO dto) {
        // Vérifier que le keynote existe si un keynoteId est fourni
        if (dto.getKeynoteId() != null) {
            try {
                KeynoteDTO keynote = keynoteRestClient.getKeynoteById(dto.getKeynoteId());
                log.info("Keynote trouvé: {} {}", keynote.getNom(), keynote.getPrenom());
            } catch (Exception e) {
                log.error("Erreur lors de la vérification du keynote", e);
                throw new RuntimeException("Keynote introuvable avec l'ID: " + dto.getKeynoteId());
            }
        }

        Conference entity = mapper.toEntity(dto);
        entity.setId(null); // Let JPA generate ID
        Conference saved = repository.save(entity);
        log.info("Conférence créée avec succès: {}", saved.getId());

        return enrichConferenceWithKeynote(saved);
    }

    public ConferenceDTO updateConference(UUID id, ConferenceDTO dto) {
        Conference existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        // Vérifier le keynote si changé
        if (dto.getKeynoteId() != null && !dto.getKeynoteId().equals(existing.getKeynoteId())) {
            try {
                keynoteRestClient.getKeynoteById(dto.getKeynoteId());
            } catch (Exception e) {
                throw new RuntimeException("Keynote introuvable avec l'ID: " + dto.getKeynoteId());
            }
        }

        existing.setTitre(dto.getTitre());
        existing.setType(dto.getType());
        existing.setDate(dto.getDate());
        existing.setDureeMinutes(dto.getDureeMinutes());
        existing.setNombreInscrits(dto.getNombreInscrits());
        existing.setScore(dto.getScore());
        existing.setKeynoteId(dto.getKeynoteId());

        Conference updated = repository.save(existing);
        log.info("Conférence mise à jour avec succès: {}", updated.getId());

        return enrichConferenceWithKeynote(updated);
    }

    public void deleteConference(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Conference not found with ID: " + id);
        }
        repository.deleteById(id);
        log.info("Conférence supprimée avec succès: {}", id);
    }

    public ConferenceDTO getConference(UUID id) {
        Conference conference = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found"));
        return enrichConferenceWithKeynote(conference);
    }

    public List<ConferenceDTO> getAllConferences() {
        return repository.findAll().stream()
                .map(this::enrichConferenceWithKeynote)
                .collect(Collectors.toList());
    }

    public List<ConferenceDTO> getConferencesByKeynote(UUID keynoteId) {
        log.info("Recherche des conférences pour le keynote: {}", keynoteId);
        return repository.findByKeynoteId(keynoteId).stream()
                .map(this::enrichConferenceWithKeynote)
                .collect(Collectors.toList());
    }

    /**
     * Méthode privée pour enrichir une conférence avec les données du keynote via OpenFeign
     */
    private ConferenceDTO enrichConferenceWithKeynote(Conference conference) {
        ConferenceDTO dto = mapper.toDto(conference);

        if (conference.getKeynoteId() != null) {
            try {
                KeynoteDTO keynote = keynoteRestClient.getKeynoteById(conference.getKeynoteId());
                dto.setKeynote(keynote);
                log.debug("Keynote enrichi pour la conférence {}: {} {}",
                        conference.getId(), keynote.getNom(), keynote.getPrenom());
            } catch (Exception e) {
                log.warn("Impossible de récupérer les informations du keynote avec l'ID: {}. Erreur: {}",
                        conference.getKeynoteId(), e.getMessage());
                // On continue sans les données du keynote
            }
        }

        return dto;
    }


}