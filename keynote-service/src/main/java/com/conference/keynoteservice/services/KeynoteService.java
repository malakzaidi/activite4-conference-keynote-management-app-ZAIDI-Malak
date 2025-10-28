package com.conference.keynoteservice.services;

import com.conference.keynoteservice.dtos.KeynoteDTO;
import com.conference.keynoteservice.entities.Keynote;
import com.conference.keynoteservice.mappers.KeynoteMapper;
import com.conference.keynoteservice.repositories.KeynoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class KeynoteService {
    private KeynoteRepository repo;
    private KeynoteMapper mapper;

    public KeynoteService(KeynoteRepository repo, KeynoteMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<KeynoteDTO> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    public KeynoteDTO findById(UUID id) {
        var k = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        return mapper.toDto(k);
    }

    public KeynoteDTO create(KeynoteDTO dto) {
        Keynote entity = mapper.toEntity(dto);
        entity.setId(null);
        var saved = repo.save(entity);
        return mapper.toDto(saved);
    }

    public KeynoteDTO update(UUID id, KeynoteDTO dto) {
        var existing = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        existing.setNom(dto.nom());
        existing.setPrenom(dto.prenom());
        existing.setEmail(dto.email());
        existing.setFonction(dto.fonction());
        return mapper.toDto(repo.save(existing));
    }

    public void delete(UUID id) {
        repo.deleteById(id);
    }



}
