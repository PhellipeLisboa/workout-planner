package com.phellipe.workoutplanner.mapper;

import com.phellipe.workoutplanner.dto.UserRequestDto;
import com.phellipe.workoutplanner.dto.UserResponseDto;
import com.phellipe.workoutplanner.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto dto) {

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();

    }

    public static UserResponseDto toDto(User user) {

        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }

}
