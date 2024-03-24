package com.example.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: 13.03.2024 czy mozna jakos rozszerzac ? np extends TrainerShortDto???
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDto {
    private long id;
    private String name;
    private String lastname;
    private String phoneNumber;
    private String description;
    private String igNickname;
    private boolean inGym;
}
