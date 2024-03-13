package com.example.gym.mapper;

import com.example.gym.dto.TrainerDto;
import com.example.gym.repository.entity.Trainer;

public class TrainerMapper {
    private TrainerMapper() {} // dla bezpieczenstwa
    public static TrainerDto toDto(Trainer trainer) {
        return TrainerDto.builder()
                .name(trainer.getName())
                .lastname(trainer.getLastname())
                .phoneNumber(trainer.getPhoneNumber())
                .build();
    }
}
