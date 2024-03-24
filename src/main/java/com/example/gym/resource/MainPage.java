package com.example.gym.resource;

import com.example.gym.dto.EventShortDto;
import com.example.gym.dto.HomeResponse;
import com.example.gym.repository.Mocker;
import com.example.gym.service.EventService;
import com.example.gym.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class MainPage {
    private final Mocker mocker;
    private final EventService eventService;
    private final TrainerService trainerService;
    @GetMapping("/mock")
    public ResponseEntity<String> mock() {
        mocker.mockEventsAndTrainers();
        return ResponseEntity.ok("zamokowalismy baze danych");
    }

    // TODO: 13.03.2024 Dorobic usera
    @GetMapping("")
    public ResponseEntity<HomeResponse> inGym() {
        Long userCount = 0L;
        Long trainerCount = trainerService.getCountTrainerInGym();
        return ResponseEntity.ok(new HomeResponse(userCount, trainerCount));
    }

    // TODO: 13.03.2024 dorobic filtrowanie jakies ciekawsze ogarnac z filtrowaniem?
    @GetMapping("/find/") // find/?keywords=
    public List<EventShortDto> find(@RequestParam(defaultValue = "") String keywords, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size){
        return keywords.isEmpty() ? eventService.getShortEvents(page, size) : eventService.getShortEventsByTitle(keywords);
    }

    // TODO: 13.03.2024 dodac funkcjonalnosc zglaszania bledow, kategoria, tytul, tekst, date, maila do osoby zglaszajacej ewentualnie miejsce na przeslanie zdjecia? 
    @PostMapping(value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> reportProblem() {
        throw new IllegalArgumentException("not yet implemented!");
    }

}
