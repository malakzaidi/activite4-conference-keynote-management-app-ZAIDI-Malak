package com.conference.conferenceservice.mappers;


import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.entities.Conference;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class}) // ✅ Add ReviewMapper
public interface ConferenceMapper {
    ConferenceDTO toDto(Conference conference);
    Conference toEntity(ConferenceDTO dto);
}