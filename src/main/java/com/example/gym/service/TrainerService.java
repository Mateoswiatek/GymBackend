package com.example.gym.service;

import com.example.gym.repository.TrainerRepository;
import com.example.gym.repository.entity.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public List<Trainer> getTrainers() {
        return trainerRepository.findAllTrainers();
    }

    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }
}
