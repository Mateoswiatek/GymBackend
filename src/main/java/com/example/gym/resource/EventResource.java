package com.example.gym.resource;

import com.example.gym.model.EventDto;
import com.example.gym.model.EventResponse;
import com.example.gym.model.EventShortDto;
import com.example.gym.model.EventShortResponse;
import com.example.gym.repository.Mocker;
import com.example.gym.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventResource {
    private final EventService eventService;
    private final Mocker mocker;
    @GetMapping("/mock")
    public ResponseEntity<String> mock() {
        mocker.mockEvents();
        return ResponseEntity.ok("zamokowalismy baze danych");
    }

    // TODO: 12.03.2024 zmodyfikowanie stronnicowania
    @GetMapping("/") // ?page=2
    public List<EventShortDto> getAll(@RequestParam(defaultValue = "1") int page) { // ResponseEntity<EventShortResponse>
        return eventService.getShortEvents();
//        eventService.getAll();
//        return ResponseEntity.ok(new EventShortResponse(eventShortDtoList));
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@RequestParam int id) { // ResponseEntity<EventResponse>
        throw new IllegalArgumentException("Nie zaimplementowano jeszcze");
    }

    @PostMapping("/adding")
    public ResponseEntity<Object> addEvent(){
        throw new IllegalArgumentException("Nie zaimplementowano jeszcze");
    }

}
