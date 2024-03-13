package com.example.gym.repository;

import com.example.gym.repository.entity.Event;
import com.example.gym.repository.entity.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Mocker {
    private final EventRepository eventRepository;

    public void mockEvents() {
        List<Event> events = new ArrayList<>(20);
        for (int i = 1; i <= 20; i++) {
            events.add(Event.builder()
                    .title("tytul" + i)
                    .description("opis")
                    .date(LocalDateTime.now())
                    .build());
//            stringBuilder.append(String.format("insert into event(id, title, description) values (%d, 'Nazwa%d', 'Opis%d');\n", i,i,i));
        }
        eventRepository.saveAll(events);
    }

    public void mockTrainers() {
        List<Trainer> trainers = new ArrayList<>(12);
        for (int i = 0; i < 10; i++) {
            trainers.add(Trainer.builder()
                    .build());
        }
    }
}
