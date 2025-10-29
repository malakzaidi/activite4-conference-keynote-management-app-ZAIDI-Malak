package com.conference.keynoteservice.web;

import com.conference.keynoteservice.dtos.KeynoteDTO;
import com.conference.keynoteservice.services.KeynoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/keynotes")
public class KeynoteController {

        private final KeynoteService keynoteService;

        public KeynoteController(KeynoteService keynoteService) {
            this.keynoteService = keynoteService;
        }

        // GET all keynotes
        @GetMapping
        public ResponseEntity<List<KeynoteDTO>> getAllKeynotes() {
            return ResponseEntity.ok(keynoteService.findAll());
        }

        //  GET keynote by ID
        @GetMapping("/{id}")
        public ResponseEntity<KeynoteDTO> getKeynoteById(@PathVariable UUID id) {
            return ResponseEntity.ok(keynoteService.findById(id));
        }

        //  CREATE a new keynote
        @PostMapping
        public ResponseEntity<KeynoteDTO> createKeynote(@RequestBody KeynoteDTO keynoteDTO) {
            return ResponseEntity.ok(keynoteService.create(keynoteDTO));
        }

        //  UPDATE a keynote
        @PutMapping("/{id}")
        public ResponseEntity<KeynoteDTO> updateKeynote(@PathVariable UUID id, @RequestBody KeynoteDTO keynoteDTO) {
            return ResponseEntity.ok(keynoteService.update(id, keynoteDTO));
        }

        //  DELETE a keynote
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteKeynote(@PathVariable UUID id) {
            keynoteService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }



