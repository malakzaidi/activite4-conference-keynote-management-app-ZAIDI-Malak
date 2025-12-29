package com.conference.conferenceservice.web;

import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.models.KeynoteDTO;
import com.conference.conferenceservice.services.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class ConferenceController {

    private final ConferenceService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ConferenceDTO> createConference(@RequestBody ConferenceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createConference(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ConferenceDTO> updateConference(@PathVariable UUID id,
                                                          @RequestBody ConferenceDTO dto) {
        return ResponseEntity.ok(service.updateConference(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteConference(@PathVariable UUID id) {
        service.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ConferenceDTO> getConference(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getConference(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ConferenceDTO>> getAllConferences() {
        return ResponseEntity.ok(service.getAllConferences());
    }

    @GetMapping("/keynote/{keynoteId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ConferenceDTO>> getConferencesByKeynote(@PathVariable UUID keynoteId) {
        return ResponseEntity.ok(service.getConferencesByKeynote(keynoteId));
    }
}
