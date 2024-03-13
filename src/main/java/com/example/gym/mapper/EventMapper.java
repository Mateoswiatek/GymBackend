package com.example.gym.mapper;

import com.example.gym.model.EventDto;
import com.example.gym.model.EventShortDto;
import com.example.gym.repository.entity.Event;

public class EventMapper {

    public static Event toEvent(EventShortDto eventShortDto){
        throw new IllegalArgumentException("Nie zaimplementowano");
    }
    public static EventShortDto toShortDto(Event event) {
        return EventShortDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .date(event.getDate())
                .build();
    }
    public static EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .build();
    }

}
