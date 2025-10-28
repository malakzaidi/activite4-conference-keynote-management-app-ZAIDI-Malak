package com.conference.keynoteservice.mappers;


import com.conference.keynoteservice.dtos.KeynoteDTO;
import com.conference.keynoteservice.entities.Keynote;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface KeynoteMapper {
    KeynoteDTO toDto(Keynote entity);
    Keynote toEntity(KeynoteDTO dto);
}
