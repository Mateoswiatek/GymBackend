package com.example.gym.resource;

import com.example.gym.dto.EventDto;
import com.example.gym.dto.EventShortDto;
import com.example.gym.dto.RequestAddUserToEvent;
import com.example.gym.dto.UserShortDto;
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
    @GetMapping("/") // /?page=1&size=5
    public List<EventShortDto> getAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) { // ResponseEntity<EventShortResponse>
        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        return eventService.getShortEvents(pageNumber-1, sizeValue);
//        return ResponseEntity.ok(new EventShortResponse(eventShortDtoList));
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable Long id) { // ResponseEntity<EventResponse>
        return eventService.getEventDtoById(id);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUserToEvent(@PathVariable Long id, @RequestBody RequestAddUserToEvent addUserToEvent) {
        eventService.addUserToEvent(id, addUserToEvent.getUserId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/participants/").build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/participants/") //?page=1&size=5
    public List<UserShortDto> getEventParticipants(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @PathVariable Long id) {
        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        return eventService.getEventParticipants(id, pageNumber-1, sizeValue);
    }






}


/* Coś w tym stylu ? jakby wbudowane kontrolery w inny kontroler?
@RequestMapping("/{id}")
    public class EventById{
        private Long id;

        public EventById(@PathVariable Long id) {
            this.id = id;
        }
        @GetMapping("")
        public EventDto getEvent(Long id) { // ResponseEntity<EventResponse>
            return eventService.getEventDtoById(id);
        }
    }
 */