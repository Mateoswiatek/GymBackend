package com.example.gym.dto;

import com.example.gym.repository.entity.Trainer;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    // liczba miejsc, wolnych i zajetych, nazwa organizatora, id organizatora - aby mozna bylo przejsc do jego strony
}
