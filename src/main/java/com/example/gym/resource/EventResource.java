package com.example.gym.resource;

import com.example.gym.dto.EventDto;
import com.example.gym.dto.EventShortDto;
import com.example.gym.service.EventService;
import jakarta.persistence.PostPersist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventResource {
    private final EventService eventService;

    // TODO: 12.03.2024 zmodyfikowanie stronnicowania
    @GetMapping("/") // /?page=0&size=5
    public List<EventShortDto> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) { // ResponseEntity<EventShortResponse>
        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        return eventService.getShortEvents(pageNumber-1, sizeValue);
//        return ResponseEntity.ok(new EventShortResponse(eventShortDtoList));
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@RequestParam int id) { // ResponseEntity<EventResponse>
        throw new IllegalArgumentException("Nie zaimplementowano jeszcze");
    }
}
