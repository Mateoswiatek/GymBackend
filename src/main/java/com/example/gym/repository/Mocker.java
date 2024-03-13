package com.example.gym.repository;

import com.example.gym.repository.entity.Event;
import com.example.gym.repository.entity.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class Mocker {
    private final EventRepository eventRepository;
    private final TrainerRepository trainerRepository;

    public void mockEvents() {
        List<Event> events = new ArrayList<>(21);
        for (int i = 1; i <= 21; i++) {
            events.add(Event.builder()
                        .title("tytul" + i)
                        .description("opis")
                        .date(LocalDateTime.now())
                    .build());
        }
        eventRepository.saveAll(events);
    }

    public void mockTrainers() {
        List<Trainer> trainers = new ArrayList<>(10);
        for (long i = 1; i <= 10; i++) {

            trainers.add(Trainer.builder()
                            .name("Nazwa" + i)
                            .lastname("Nazwisko" + i)
                            .phoneNumber(String.valueOf(i))
                            .events(eventRepository.findAllById(List.of(i, 21-i)))
                    .build());
        }
        trainers.add(Trainer.builder()
                        .name("NazwaX")
                        .lastname("NazwiskoX")
                        .phoneNumber("999")
                        .events(List.of(Objects.requireNonNull(eventRepository.findById(21L).orElse(null))))
                .build());
        trainers.add(Trainer.builder()
                .name("NazwaBez")
                .lastname("NazwiskoBez")
                .phoneNumber("777")
                .build());
        trainerRepository.saveAll(trainers);
    }
}
