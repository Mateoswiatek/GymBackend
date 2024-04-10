package com.example.gym.resource;

import com.example.gym.dto.EventShortResponse;
import com.example.gym.dto.TrainerDto;
import com.example.gym.mapper.TrainerMapper;
import com.example.gym.dto.TrainerShortDto;
import com.example.gym.repository.entity.Trainer;
import com.example.gym.service.EventService;
import com.example.gym.service.TrainerService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainers")
@RequiredArgsConstructor
public class TrainerResource {
    private final TrainerService trainerService;
    private final EventService eventService;
    @GetMapping("/") // trainer/?page=0&size=5
    public List<TrainerShortDto> getTrainers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        return trainerService.getTrainers(
                pageNumber-1, sizeValue)
                .stream().map(TrainerMapper::toShortDto).toList();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TrainerDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Trainer not found",
                    content = @Content) })
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
        return trainer.map(value -> ResponseEntity.ok(TrainerMapper.toDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/events/") // /?page=0&size=5
    public ResponseEntity<EventShortResponse> getEventsByTrainerId(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        /*
        Optional<Trainer> trainer = trainerService.getTrainerById(id);
        if(trainer.isPresent()){
            return ResponseEntity.ok(new EventShortResponse(eventService.getShortEventsByTrainerId(id)));
        } else {
            return ResponseEntity.notFound().build();
        }
         */

        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        Optional<Trainer> trainerOptional = trainerService.getTrainerById(id);

        return trainerOptional.map(trainer -> ResponseEntity.ok(new EventShortResponse(eventService.getShortEventsByTrainerId(id, pageNumber-1, sizeValue))))
                .orElse(ResponseEntity.notFound().build());
        // To zwraca ResponseEntity<String>
        // ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trainer with ID: " + id + " not found."));

//        A tu bez sprawdzania czy istnieje taki Trener
//        return ResponseEntity.ok(new EventShortResponse(eventService.getShortEventsByTrainerId(id)));
    }
}
