package com.conference.conferenceservice.web;

import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.services.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
    public class ConferenceController {

        private  ConferenceService service;

        // Créer une conférence
        @PostMapping
        public ResponseEntity<ConferenceDTO> create(@RequestBody ConferenceDTO dto) {
            ConferenceDTO saved = service.createConference(dto);
            return ResponseEntity.ok(saved);
        }

        // Mettre à jour une conférence
        @PutMapping("/{id}")
        public ResponseEntity<ConferenceDTO> update(@PathVariable UUID id,
                                                    @RequestBody ConferenceDTO dto) {
            ConferenceDTO updated = service.updateConference(id, dto);
            return ResponseEntity.ok(updated);
        }

        // Supprimer une conférence
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable UUID id) {
            service.deleteConference(id);
            return ResponseEntity.noContent().build();
        }

        // Obtenir une conférence par son ID
        @GetMapping("/{id}")
        public ResponseEntity<ConferenceDTO> get(@PathVariable UUID id) {
            ConferenceDTO dto = service.getConference(id);
            return ResponseEntity.ok(dto);
        }

        // Obtenir toutes les conférences
        @GetMapping
        public ResponseEntity<List<ConferenceDTO>> getAll() {
            List<ConferenceDTO> conferences = service.getAllConferences();
            return ResponseEntity.ok(conferences);
        }
}
