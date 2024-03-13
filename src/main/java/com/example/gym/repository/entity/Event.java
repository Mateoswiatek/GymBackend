package com.example.gym.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Event {
    @Id
    private long id;
    private String title;
    private String description;
    private LocalDateTime date;
}
