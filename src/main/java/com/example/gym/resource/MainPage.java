package com.example.gym.resource;

import com.example.gym.dto.EventShortDto;
import com.example.gym.repository.Mocker;
import com.example.gym.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainPage {
    private final Mocker mocker;
    private final EventService eventService;
    @GetMapping("/mock")
    public ResponseEntity<String> mock() {
        mocker.mockEvents();
        mocker.mockTrainers();
        return ResponseEntity.ok("zamokowalismy baze danych");
    }

    @GetMapping("/find/") // find/?keywords=
    public List<EventShortDto> find(@RequestParam(defaultValue = "") String keywords) {
        return keywords.isEmpty() ? eventService.getShortEvents() : eventService.getShortEventsByTitle(keywords);
    }

}
