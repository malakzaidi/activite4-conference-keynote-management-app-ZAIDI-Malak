package com.conference.conferenceservice.mappers;

import com.conference.conferenceservice.dtos.ReviewDTO;
import com.conference.conferenceservice.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO toDto(Review entity);

    @Mapping(target = "conference", ignore = true)
    Review toEntity(ReviewDTO dto);
}