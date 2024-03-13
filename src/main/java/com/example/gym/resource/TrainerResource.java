package com.example.gym.resource;

import com.example.gym.repository.entity.Trainer;
import com.example.gym.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerResource {
    private final TrainerService trainerService;

    @GetMapping("")
    public List<Trainer> getTrainers() {
        return  trainerService.getTrainers();
    }
}
