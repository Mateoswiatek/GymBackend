package com.example.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerShortDto {
    private long id;
    private String name;
    private String lastname;
    private String phoneNumber;
    private boolean inGym;
}
