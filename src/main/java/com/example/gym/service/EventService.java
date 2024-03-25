package com.example.gym.service;

import com.example.gym.dto.EventDto;
import com.example.gym.dto.UserShortDto;
import com.example.gym.mapper.EventMapper;
import com.example.gym.dto.EventShortDto;
import com.example.gym.mapper.UserMapper;
import com.example.gym.repository.EventRepository;
import com.example.gym.repository.UserRepository;
import com.example.gym.repository.entity.Event;
import com.example.gym.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

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

    public EventDto getEventDtoById(Long id) {
        Optional<Event> optional = eventRepository.findByIdWithTrainer(id);
        if(optional.isEmpty()) {
            throw new RuntimeException("no data, Event with id=" + id);
        }
        return EventMapper.toDto(optional.get());
    }

    public List<UserShortDto> getEventParticipants(Long id, int page, int size) {
        return UserMapper.toShortDtoList(eventRepository.findParticipantsByEventId(id, PageRequest.of(page, size)));
    }

    public void addUserToEvent(Long eventId, Long userId) {

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if(optionalEvent.isEmpty()) {
            throw new RuntimeException("no data, Event with id=" + eventId);
        }
        Event event = optionalEvent.get();

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("no data, User with id=" + userId);
        }
        User user = optionalUser.get();

        if(event.getParticipants().contains(user)) {
            throw new RuntimeException("you are already registered. UserId=" + userId + ", EventId=" + eventId);
        }

        event.getParticipants().add(user);
        user.getEvents().add(event);
        eventRepository.save(event);
        userRepository.save(user);
    }
}
