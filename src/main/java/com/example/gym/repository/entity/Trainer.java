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
    // TODO: 13.03.2024 czy nie lepiej robic Seta ?
    @OneToMany
//    @JoinColumn(name = "trainer_id") ???
    private List<Event> events;
}
