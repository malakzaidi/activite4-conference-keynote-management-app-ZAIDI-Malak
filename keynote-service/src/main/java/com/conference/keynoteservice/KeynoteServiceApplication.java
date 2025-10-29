package com.conference.keynoteservice;

import com.conference.keynoteservice.dtos.KeynoteDTO;
import com.conference.keynoteservice.entities.Keynote;
import com.conference.keynoteservice.repositories.KeynoteRepository;
import com.conference.keynoteservice.services.KeynoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class KeynoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(KeynoteService keynoteService) {
        return args -> {

            keynoteService.create(KeynoteDTO.builder()
                    .nom("ZAIDI")
                    .prenom("Malak")
                    .email("zaidimalak@example.com")
                    .fonction("Software Architect")
                    .build());

            keynoteService.create(KeynoteDTO.builder()
                    .nom("karim")
                    .prenom("Ahmed")
                    .email("karimahmed@example.com")
                    .fonction("Cloud Engineer")
                    .build());

            keynoteService.create(KeynoteDTO.builder()
                    .nom("Sami")
                    .prenom("Baid")
                    .email("samibaid@example.com")
                    .fonction("DevOps Lead")
                    .build());

            System.out.println(" Sample keynotes initialized successfully!");

            // Display all keynotes
            keynoteService.findAll().forEach(k ->
                    System.out.println(k.getPrenom() + " " + k.getNom() + " - " + k.getFonction())
            );
        };

    }

}
