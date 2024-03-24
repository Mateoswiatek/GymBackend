package com.example.gym.repository.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "TRAINER_ID", nullable = false)
    private Trainer trainer;
    // TODO: 24.03.2024 Dodać informację dla userow ktorzy się zapisują.
    // TODO: 24.03.2024 Dodać lokalizację / nazwę lokalizacji gdzie event się odbywa
}

/*
dodac czy jest max ilosc miejsc, jesli nie podano to null?
dodac mozliwosc zapisu na event, userzy moga sie zapisywac na event
 */
