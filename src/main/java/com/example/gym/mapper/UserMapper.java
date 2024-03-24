package com.example.gym.mapper;

import com.example.gym.dto.UserShortDto;
import com.example.gym.repository.entity.User;

import java.util.List;

public class UserMapper {
    private UserMapper() {}

    public static UserShortDto toShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .fitnessLevel(user.getFitnessLevel())
                .atGym(user.isAtGym())
            .build();
    }

    public static List<UserShortDto> toShortDtoList(List<User> userList) {
        return userList.stream().map(UserMapper::toShortDto).toList();
    }
}
