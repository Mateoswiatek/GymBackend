package com.example.gym.service;

import com.example.gym.mapper.EventMapper;
import com.example.gym.model.EventShortDto;
import com.example.gym.repository.EventRepository;
import com.example.gym.repository.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    public List<EventShortDto> getShortEvents() {
        return eventRepository.findAll().stream().map(EventMapper::toShortDto).toList();
    }

    public List<EventShortDto> getShortEventsByTitle(String title){
        return eventRepository.findAllByTitle(title).stream().map(EventMapper::toShortDto).toList();
    }
}
