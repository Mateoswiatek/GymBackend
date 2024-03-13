package com.example.gym.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime date;
    @ManyToOne
    private Trainer trainer;
}

/*
dodac czy jest max ilosc miejsc, jesli nie podano to null?
dodac mozliwosc zapisu na event, userzy moga sie zapisywac na event
 */
