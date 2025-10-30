package com.conference.conferenceservice.mappers;

import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.entities.Conference;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConferenceMapper {

    ConferenceDTO toDto(Conference conference);

    Conference toEntity(ConferenceDTO conferenceDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(ConferenceDTO conferenceDTO, @MappingTarget Conference conference);
}