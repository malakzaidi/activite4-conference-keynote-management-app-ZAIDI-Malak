package com.conference.keynoteservice.web;

import com.conference.keynoteservice.dtos.KeynoteDTO;
import com.conference.keynoteservice.services.KeynoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/keynotes")
public class keynoteController {
    private KeynoteService srv;
    public keynoteController(KeynoteService srv){
        this.srv = srv;
    }

    @GetMapping
    public List<KeynoteDTO> all() { return srv.findAll(); }

    @GetMapping("/{id}")
    public KeynoteDTO byId(@PathVariable UUID id) { return srv.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KeynoteDTO create(@RequestBody KeynoteDTO dto) { return srv.create(dto); }

    @PutMapping("/{id}")
    public KeynoteDTO update(@PathVariable UUID id, @RequestBody KeynoteDTO dto) { return srv.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) { srv.delete(id); }
}

