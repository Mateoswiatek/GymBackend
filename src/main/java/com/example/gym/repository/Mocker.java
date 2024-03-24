package com.example.gym.repository;

import com.example.gym.repository.entity.Event;
import com.example.gym.repository.entity.Trainer;
import com.example.gym.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class Mocker {
    private final EventRepository eventRepository;
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    @Transactional // ??? cos chyba nie dziala tak jak powinno
    public void mockEventsAndTrainers() {
        mockTrainers();
        mockEvents();
        mockUsers();
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

    public void mockUsers() {
        List<User> users = new ArrayList<>(15);
        List<Event> eventList = eventRepository.findAll();
        Random random = new Random();
        for (long i = 1; i <= 10; i++) {
            int numberOfRandomEvents = random.nextInt(10);
            List<Event> randomEvents = new ArrayList<>(10);
            for (int j = 0; j < numberOfRandomEvents; j++) {
                randomEvents.add(eventList.get(random.nextInt(eventList.size())));
            }

            User user = User.builder()
                    .name("User" + i)
                    .lastName("Nazwisko" + i)
                    .gender(random.nextBoolean())
                    .emailAddress("adres" + i + "@gmail.com")
                    .dateOfBirth(LocalDate.now().minusYears((60 - random.nextInt(55))))
                    .registrationDate(LocalDate.now().minusMonths(random.nextLong(120)))
                    .fitnessLevel(random.nextInt(10)+1)
                    .atGym(random.nextBoolean())
                    .events(randomEvents)
                .build();
//            randomEvents.forEach(e -> e.getParticipants().add(user));
            for(Event e : randomEvents) {
                if(e.getParticipants() == null) {
                    e.setParticipants(new ArrayList<>());
                }
                e.getParticipants().add(user);
            }

            users.add(user);
        }

        users.add(User.builder()
                .name("UserBez")
                .lastName("NazwiskoBez")
                .gender(random.nextBoolean())
                .emailAddress("adresBez@gmail.com")
                .dateOfBirth(LocalDate.now().minusYears((60 - random.nextInt(55))))
                .registrationDate(LocalDate.now().minusMonths(random.nextLong(120)))
                .fitnessLevel(random.nextInt(10)+1)
                .atGym(random.nextBoolean())
            .build());

        userRepository.saveAll(users);
    }
}
