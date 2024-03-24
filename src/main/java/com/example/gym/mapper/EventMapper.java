package com.example.gym.mapper;

import com.example.gym.dto.EventDto;
import com.example.gym.dto.EventShortDto;
import com.example.gym.repository.entity.Event;

import java.util.List;

public class EventMapper {
    private EventMapper() {}

    public static Event toEvent(EventShortDto eventShortDto){
        throw new IllegalArgumentException("Nie zaimplementowano");
    }
    public static EventShortDto toShortDto(Event event) {
        return EventShortDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .startDate(event.getStartDate())
                .trainerId(event.getTrainer().getId())
                .trainerName(event.getTrainer().getName())
                .build();
    }
    public static List<EventShortDto> toShortDtoList(List<Event> eventList) {
        return eventList.stream().map(EventMapper::toShortDto).toList();
    }
    public static EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .build();
    }
    public static List<EventDto> toDtoList(List<Event> eventList) {
        return eventList.stream().map(EventMapper::toDto).toList();
    }

}
