package com.conference.conferenceservice.feign;

import com.conference.conferenceservice.models.KeynoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "keynote-service")
public interface KeynoteRestClient {

    @GetMapping("/api/keynotes/{id}")
    KeynoteDTO getKeynoteById(@PathVariable UUID id);

    @GetMapping("/api/keynotes")
    List<KeynoteDTO> getAllKeynotes();
}