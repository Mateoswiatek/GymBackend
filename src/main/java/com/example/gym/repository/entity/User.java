package com.example.gym.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private boolean gender;
    private String emailAddress;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private Integer fitnessLevel; // poziom zaawansowania
    private boolean atGym;
    @ManyToMany(mappedBy = "participants")
    List<Event> events;
}