package com.conference.keynoteservice.mappers;


import com.conference.keynoteservice.dtos.KeynoteDTO;
import com.conference.keynoteservice.entities.Keynote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KeynoteMapper {
    KeynoteMapper INSTANCE = Mappers.getMapper(KeynoteMapper.class);

    KeynoteDTO toDto(Keynote entity);
    Keynote toEntity(KeynoteDTO dto);
}