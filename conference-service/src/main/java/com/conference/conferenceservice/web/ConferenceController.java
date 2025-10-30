package com.conference.conferenceservice.web;

import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.models.KeynoteDTO;
import com.conference.conferenceservice.services.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conferences")
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceService service;

    @PostMapping
    public ResponseEntity<ConferenceDTO> createConference(@RequestBody ConferenceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createConference(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConferenceDTO> updateConference(@PathVariable UUID id,
                                                          @RequestBody ConferenceDTO dto) {
        return ResponseEntity.ok(service.updateConference(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable UUID id) {
        service.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDTO> getConference(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getConference(id));
    }

    @GetMapping
    public ResponseEntity<List<ConferenceDTO>> getAllConferences() {
        return ResponseEntity.ok(service.getAllConferences());
    }

    @GetMapping("/keynote/{keynoteId}")
    public ResponseEntity<List<ConferenceDTO>> getConferencesByKeynote(@PathVariable UUID keynoteId) {
        return ResponseEntity.ok(service.getConferencesByKeynote(keynoteId));
    }


}
