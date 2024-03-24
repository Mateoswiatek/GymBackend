package com.example.gym.service;

import com.example.gym.mapper.EventMapper;
import com.example.gym.dto.EventShortDto;
import com.example.gym.repository.EventRepository;
import com.example.gym.repository.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<EventShortDto> getShortEvents(int page, int size) {
        // To się wywala nie wiadomo czemu, czemu nie można bezposrednio podawać listy Idików?
//        return EventMapper.toShortDtoList(eventRepository.findAllByIdWithTrainer(eventRepository.findAll(PageRequest.of(page, size)).stream().map(Event::getId).toList()));

//        return EventMapper.toShortDtoList(eventRepository.findAllByIdWithTrainer(Arrays.asList(1L, 2L, 3L, 4L, 5L)));
        return EventMapper.toShortDtoList(eventRepository.findAllByIdWithTrainer(eventRepository.findAllEventIds(PageRequest.of(page, size))));
    }

    public List<EventShortDto> getShortEventsByTitle(String title){
        return EventMapper.toShortDtoList(eventRepository.findAllByTitle(title));
    }

    public List<EventShortDto> getShortEventsByTrainerId(Long id, int page, int size) {
        //
        return EventMapper.toShortDtoList(eventRepository.findAllByTrainerId(id, PageRequest.of(page, size)));
    }
}
