package com.conference.conferenceservice;

import com.conference.conferenceservice.dtos.ConferenceDTO;
import com.conference.conferenceservice.dtos.ReviewDTO;
import com.conference.conferenceservice.enums.TypeConference;
import com.conference.conferenceservice.services.ConferenceService;
import com.conference.conferenceservice.services.ReviewService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConferenceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ConferenceService conferenceService, ReviewService reviewService) {
        return args -> {
            // Create conferences
            ConferenceDTO conf1 = conferenceService.createConference(ConferenceDTO.builder()
                    .titre("Spring Boot Microservices")
                    .type(TypeConference.ACADEMIQUE).date(LocalDate.of(2025, 6, 15))
                    .dureeMinutes(120)
                    .nombreInscrits(150)
                    .score(4.8)
                    .build());

            ConferenceDTO conf2 = conferenceService.createConference(ConferenceDTO.builder()
                    .titre("Cloud Architecture Patterns")
                    .type(TypeConference.ACADEMIQUE).date(LocalDate.of(2025, 6, 15))
                    .date(LocalDate.of(2025, 7, 20))
                    .dureeMinutes(90)
                    .nombreInscrits(200)
                    .score(4.5)
                    .build());

            ConferenceDTO conf3 = conferenceService.createConference(ConferenceDTO.builder()
                    .titre("Agile Leadership")
                    .type(TypeConference.COMMERCIALE)
                    .date(LocalDate.of(2025, 8, 10))
                    .dureeMinutes(180)
                    .nombreInscrits(80)
                    .score(4.2)
                    .build());

            System.out.println(  + conferenceService.getAllConferences().size() + " conferences created!");

            // Create reviews for first conference
            reviewService.createReview(conf1.getId(), ReviewDTO.builder()
                    .texte("Excellent présentation sur les microservices!")
                    .note(5)
                    .build());

            reviewService.createReview(conf1.getId(), ReviewDTO.builder()
                    .texte("Très instructif, j'ai appris beaucoup de choses.")
                    .note(4)
                    .build());

            // Create reviews for second conference
            reviewService.createReview(conf2.getId(), ReviewDTO.builder()
                    .texte("Bonne conférence mais un peu trop technique.")
                    .note(4)
                    .build());

            reviewService.createReview(conf2.getId(), ReviewDTO.builder()
                    .texte("Contenu de qualité, speaker très compétent.")
                    .note(5)
                    .build());

            reviewService.createReview(conf2.getId(), ReviewDTO.builder()
                    .texte("Aurait mérité plus de temps pour les questions.")
                    .note(3)
                    .build());

            System.out.println(" Sample reviews created!");

            // Display all conferences with review count
            conferenceService.getAllConferences().forEach(c -> {
                List<ReviewDTO> reviews = reviewService.getReviewsByConference(c.getId());
                System.out.println( c.getTitre() + " - " + c.getType() +
                        " (" + reviews.size() + " reviews)");
            });
        };
    }
}