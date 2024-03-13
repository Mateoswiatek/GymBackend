package com.example.gym.resource;

import com.example.gym.dto.EventShortResponse;
import com.example.gym.dto.TrainerDto;
import com.example.gym.mapper.TrainerMapper;
import com.example.gym.dto.TrainerShortDto;
import com.example.gym.repository.entity.Trainer;
import com.example.gym.service.EventService;
import com.example.gym.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerResource {
    private final TrainerService trainerService;
    private final EventService eventService;
    // TODO: 13.03.2024 zrobić wiecej detalis na temat trenera, w tedy one beda sie wyswietly na jego tablicy. tak aby bylo zroznicowane TrainerShortDto, TrainerDto

    // TODO: 13.03.2024 tutaj bedziemy zwracac mniej szczegulowe informacje na temat trenerow, bedzie TrainerShortDto
    @GetMapping("/") // trainer/?page=0&size=5
    public List<TrainerShortDto> getTrainers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        return  trainerService.getTrainers(pageNumber-1, sizeValue).stream().map(TrainerMapper::toShortDto).toList();
    }

    // TODO: 13.03.2024 dodac opcje szukania po nazwie?
    // TODO N+1 Hibernate
    @GetMapping("/{id}")
    public ResponseEntity<TrainerDto> getTrainer(@PathVariable Long id) {
        Optional<Trainer> trainer = trainerService.getTrainerById(id);

        /*
        if(trainer.isPresent()){
            return ResponseEntity.ok(TrainerMapper.toDto(trainer.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
         */
//       old return trainer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        return trainer.map(value -> ResponseEntity.ok(TrainerMapper.toDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TODO: 13.03.2024 moze da sie jakos ladniej podpiac czesc z eventami do tego?
    @GetMapping("/{id}/events")
    public ResponseEntity<EventShortResponse> getEventsByTrainerId(@PathVariable Long id) {
        /*
        Optional<Trainer> trainer = trainerService.getTrainerById(id);
        if(trainer.isPresent()){
            return ResponseEntity.ok(new EventShortResponse(eventService.getShortEventsByTrainerId(id)));
        } else {
            return ResponseEntity.notFound().build();
        }
         */
        Optional<Trainer> trainerOptional = trainerService.getTrainerById(id);
        return trainerOptional.map(trainer -> ResponseEntity.ok(new EventShortResponse(eventService.getShortEventsByTrainerId(id))))
                .orElse(ResponseEntity.notFound().build());
        // To zwraca ResponseEntity<String>
        // ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer with ID: " + id + " not found."));

//        A tu bez sprawdzania czy istnieje taki Trener
//        return ResponseEntity.ok(new EventShortResponse(eventService.getShortEventsByTrainerId(id)));
    }

    // TODO: 13.03.2024 czy takie api ma sens, tutaj przekazujemy rzeczy i odpalamy metode z EventService?
    @PostMapping("/{id}/events/adding")
    public ResponseEntity<Object> addEvent(){
        throw new IllegalArgumentException("Nie zaimplementowano jeszcze");
    }
}
