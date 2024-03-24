package com.example.gym.repository;

import com.example.gym.repository.entity.Event;
import com.example.gym.repository.entity.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(3))
            .build());
        }
        events.add(Event.builder()
                .title("PrzyszlyEvent")
                .description("opisPrzyszlosci")
                .startDate(LocalDateTime.now().plusDays(10))
                .endDate(LocalDateTime.now().plusDays(11))
            .build());
        eventRepository.saveAll(events);
    }

    public void mockTrainers() {
        List<Trainer> trainers = new ArrayList<>(10);
        Random random = new Random();
        for (long i = 1; i <= 10; i++) {

            trainers.add(Trainer.builder()
                            .name("Nazwa" + i)
                            .lastname("Nazwisko" + i)
                            .phoneNumber(String.valueOf(i))
                            .description("Opis " + i)
                            .igNickname("nazwaNaInsta " + i)
                            .inGym(random.nextBoolean())
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
