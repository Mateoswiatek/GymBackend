package com.example.gym.resource;

import com.example.gym.dto.*;
import com.example.gym.mapper.TrainerMapper;
import com.example.gym.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    @GetMapping("") // /?page=1&size=5
    public List<EventShortDto> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Sort.Direction direction) { // ResponseEntity<EventShortResponse>
        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        return eventService.getShortEvents(pageNumber-1, sizeValue, direction);
//        return ResponseEntity.ok(new EventShortResponse(eventShortDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Long id) { // ResponseEntity<EventResponse>
        EventDto eventDto = eventService.getEventDtoById(id);
        return eventDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(eventDto);
    }

    @PutMapping(path = "/{id}/participants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addUserToEvent(@PathVariable Long id, @RequestBody RequestAddUserToEvent addUserToEvent) {
        eventService.addUserToEvent(id, addUserToEvent.getUserId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("").build().toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}/participants") //?page=1&size=5
    public List<UserShortDto> getEventParticipants(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @PathVariable Long id) {
        int pageNumber = page > 0 ? page : 1;
        int sizeValue = size > 0 ? size : 10;
        return eventService.getEventParticipants(id, pageNumber-1, sizeValue);
    }

    // tu z tokena + zabezpiecenia aby tylko trenery mogli dodawać.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addEvent(@RequestBody EventDto eventDto) {
        long id = eventService.addEvent(eventDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    //TODO moze jednak przez id edytowac? co najlepiej byłoby zwracać?
    @PutMapping("/{id}")
    public EventDto editEvent(@RequestBody EventEditDto eventEditDto, @PathVariable Long id) {
        return eventService.editEvent(eventEditDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
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