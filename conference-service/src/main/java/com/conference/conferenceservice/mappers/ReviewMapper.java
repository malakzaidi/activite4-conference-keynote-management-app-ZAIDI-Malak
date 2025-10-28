package com.conference.conferenceservice.mappers;

import com.conference.conferenceservice.dtos.ReviewDTO;
import com.conference.conferenceservice.entities.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toDto(Review review);
    Review toEntity(ReviewDTO dto);
}
