package com.example.gym.service;

import com.example.gym.repository.TrainerRepository;
import com.example.gym.repository.entity.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public List<Trainer> getTrainers(int page, int size) {
        return trainerRepository.findAllTrainers(PageRequest.of(page, size)); // zmiana
    }

    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }

    public Long getCountTrainerInGym() { return trainerRepository.countAllByInGymIsTrue();}
}
