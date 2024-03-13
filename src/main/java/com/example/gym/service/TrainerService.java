package com.example.gym.service;

import com.example.gym.repository.TrainerRepository;
import com.example.gym.repository.entity.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public List<Trainer> getTrainers() {
        return trainerRepository.findAll();
    }
}
