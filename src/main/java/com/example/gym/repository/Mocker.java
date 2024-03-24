package com.example.gym.repository;

import com.example.gym.repository.entity.Event;
import com.example.gym.repository.entity.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class Mocker {
    private final EventRepository eventRepository;
    private final TrainerRepository trainerRepository;

    @Transactional // ??? cos chyba nie dziala tak jak powinno
    public void mockEventsAndTrainers() {
        mockTrainers();
        mockEvents();
    }

    public void mockEvents() {
        List<Event> events = new ArrayList<>(21);
        List<Trainer> trainers = trainerRepository.findAll();
        for (int i = 1; i <= 21; i++) {
            Trainer trainer = trainers.get(i % trainers.size());
            events.add(Event.builder()
                .title("tytul" + i)
                .description("opis")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(3))
                .trainer(trainer)
            .build());
        }
        events.add(Event.builder()
                .title("PrzyszlyEvent")
                .description("opisPrzyszlosci")
                .startDate(LocalDateTime.now().plusDays(10))
                .endDate(LocalDateTime.now().plusDays(11))
                .trainer(trainers.get(0))
            .build());
        eventRepository.saveAll(events);

        trainerRepository.save(Trainer.builder()
                .name("NazwaBezEventu")
                .lastname("NazwiskoBezEventu")
                .phoneNumber("777")
                .build());
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
                    .build());
        }
        trainers.add(Trainer.builder()
                        .name("NazwaX")
                        .lastname("NazwiskoX")
                        .phoneNumber("999")
                .build());
        trainerRepository.saveAll(trainers);
    }
}
