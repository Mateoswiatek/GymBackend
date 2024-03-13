package com.example.gym.resource;

import com.example.gym.repository.entity.Trainer;
import com.example.gym.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerResource {
    private final TrainerService trainerService;

    // TODO: 13.03.2024 tutaj bedziemy zwracac mniej szczegulowe informacje na temat trenerow, bedzie TrainerShortDto
    @GetMapping("")
    public List<Trainer> getTrainers() {
        return  trainerService.getTrainers();
    }

    // TODO: 13.03.2024 dodac opcje szukania po nazwie?
    // TODO N+1 Hibernate
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainer(@PathVariable Long id) {
        Optional<Trainer> trainer = trainerService.getTrainerById(id);
        /*
        if(trainer.isPresent()){
            return ResponseEntity.ok(trainer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
         */
        return trainer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
