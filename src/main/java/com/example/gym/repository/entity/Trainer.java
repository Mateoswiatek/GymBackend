package com.example.gym.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Trainer {
    @Id
    private long id;
    @OneToMany
    private List<Event> events;
}
