package com.example.gym.service;

import com.example.gym.mapper.EventMapper;
import com.example.gym.dto.EventShortDto;
import com.example.gym.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    public List<EventShortDto> getShortEvents() {
        return EventMapper.toShortDtoList(eventRepository.findAll(PageRequest.of(0, 5)).toList());
    }

    public List<EventShortDto> getShortEventsByTitle(String title){
        return EventMapper.toShortDtoList(eventRepository.findAllByTitle(title));
    }

    public List<EventShortDto> getShortEventsByTrainerId(Long id) {
        return EventMapper.toShortDtoList(eventRepository.findAllByTrainerId(id));
    }
}
