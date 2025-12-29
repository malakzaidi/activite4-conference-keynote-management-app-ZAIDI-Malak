package com.conference.keynoteservice.web;

import com.conference.keynoteservice.dtos.KeynoteDTO;
import com.conference.keynoteservice.services.KeynoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/keynotes")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class keynoteController {

    private final KeynoteService keynoteService;

    public keynoteController(KeynoteService keynoteService) {
        this.keynoteService = keynoteService;
    }

    // GET all keynotes
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<KeynoteDTO>> getAllKeynotes() {
        return ResponseEntity.ok(keynoteService.findAll());
    }

    //  GET keynote by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<KeynoteDTO> getKeynoteById(@PathVariable UUID id) {
        return ResponseEntity.ok(keynoteService.findById(id));
    }

    //  CREATE a new keynote
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KeynoteDTO> createKeynote(@RequestBody KeynoteDTO keynoteDTO) {
        return ResponseEntity.ok(keynoteService.create(keynoteDTO));
    }

    //  UPDATE a keynote
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<KeynoteDTO> updateKeynote(@PathVariable UUID id, @RequestBody KeynoteDTO keynoteDTO) {
        return ResponseEntity.ok(keynoteService.update(id, keynoteDTO));
    }

    //  DELETE a keynote
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteKeynote(@PathVariable UUID id) {
        keynoteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



