package com.example.gym.repository.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "TRAINER_ID", nullable = false)
    private Trainer trainer;

    //https://www.baeldung.com/jpa-many-to-many
    @ManyToMany
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> participants;
    // TODO: 24.03.2024 Dodać informację dla userow ktorzy się zapisują.
    // TODO: 24.03.2024 Dodać lokalizację / nazwę lokalizacji gdzie event się odbywa
}

/*
dodac czy jest max ilosc miejsc, jesli nie podano to null?
dodac mozliwosc zapisu na event, userzy moga sie zapisywac na event
 */
