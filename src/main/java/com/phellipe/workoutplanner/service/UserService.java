package com.phellipe.workoutplanner.service;

import com.phellipe.workoutplanner.dto.UserRequestDto;
import com.phellipe.workoutplanner.dto.UserResponseDto;
import com.phellipe.workoutplanner.dto.UserUpdateDto;
import com.phellipe.workoutplanner.entity.User;
import com.phellipe.workoutplanner.exception.UserAlreadyExistsException;
import com.phellipe.workoutplanner.exception.UserNotFoundException;
import com.phellipe.workoutplanner.mapper.UserMapper;
import com.phellipe.workoutplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto save(UserRequestDto dto) {

        if (userRepository.existsByEmail(dto.email())) {
            throw new UserAlreadyExistsException(dto.email());
        }

        User user = UserMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return UserMapper.toDto(savedUser);

    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );

        return UserMapper.toDto(user);
    }

    public List<UserResponseDto> findAll()
    {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserResponseDto update(Long id, UserUpdateDto dto) {

        User userEntity = findEntityById(id);

        if (dto.name() != null) userEntity.setName(dto.name());
        if (dto.email() != null && !dto.email().equals(userEntity.getEmail())) {
            if (userRepository.existsByEmail(dto.email())) {
                throw new UserAlreadyExistsException(dto.email());
            }
            userEntity.setEmail(dto.email());
        }
        if (dto.password() != null) userEntity.setPassword(dto.password());

        User updatedUser = userRepository.save(userEntity);
        return UserMapper.toDto(updatedUser);
    }

    public void deleteById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    private User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
