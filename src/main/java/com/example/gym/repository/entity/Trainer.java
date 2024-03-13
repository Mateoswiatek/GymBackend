package com.example.gym.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String lastname;
    private String phoneNumber;
//    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean inGym;
    // TODO: 13.03.2024 czy nie lepiej robic Seta ?
    @OneToMany
    @JoinColumn(name = "TRAINER_ID") // musi byc, w tedy do Eventu dodawany jest klucz obcy tego trenera. w innym wypadku tworzona jest nowa tabela, w zaleznosci od krotnosci obiektow
    private List<Event> events;
}
