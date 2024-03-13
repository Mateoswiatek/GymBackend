package com.example.gym.resource;

import com.example.gym.model.EventShortDto;
import com.example.gym.model.EventShortResponse;
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
    @GetMapping("/test")
    public ResponseEntity<String> testowe() {
        return ResponseEntity.ok("testowe, wszystko dziala");
    }

    // TODO: 12.03.2024 zmodyfikowanie stronnicowania
    @GetMapping("/") // ?page=2
    public ResponseEntity<EventShortResponse> getAll(@RequestParam(defaultValue = "1") int page) {
        List<EventShortDto> eventShortDtoList = new ArrayList<>();
        eventShortDtoList.add(EventShortDto.builder().id((long)page).build());
        eventShortDtoList.add(EventShortDto.builder().id((long)page).title("tytul" + page).build());
//        eventService.getAll();
        return ResponseEntity.ok(new EventShortResponse(eventShortDtoList));
    }


    @PostMapping("/adding")
    public ResponseEntity<String> addEvent(){
        throw new IllegalArgumentException("Nie zaimplementowano jeszcze");
    }

}
