package com.example.gym.resource;

import com.example.gym.repository.Mocker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainPage {
    private final Mocker mocker;
    @GetMapping("/mock")
    public ResponseEntity<String> mock() {
        mocker.mockEvents();
        mocker.mockTrainers();
        return ResponseEntity.ok("zamokowalismy baze danych");
    }

}
