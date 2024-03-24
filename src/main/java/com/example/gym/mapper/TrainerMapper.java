package com.example.gym.mapper;

import com.example.gym.dto.TrainerDto;
import com.example.gym.dto.TrainerShortDto;
import com.example.gym.repository.entity.Trainer;

public class TrainerMapper {
    private TrainerMapper() {} // dla bezpieczenstwa
    public static TrainerShortDto toShortDto(Trainer trainer) {
        return TrainerShortDto.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .lastname(trainer.getLastname())
                .phoneNumber(trainer.getPhoneNumber())
                .inGym(trainer.isInGym())
            .build();
    }
    public static TrainerDto toDto(Trainer trainer) {
        return TrainerDto.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .lastname(trainer.getLastname())
                .phoneNumber(trainer.getPhoneNumber())
                .description(trainer.getDescription())
                .igNickname(trainer.getIgNickname())
                .inGym(trainer.isInGym())
            .build();
    }
}
