package com.phellipe.workoutplanner.service;

import com.phellipe.workoutplanner.dto.UserRequestDto;
import com.phellipe.workoutplanner.dto.UserResponseDto;
import com.phellipe.workoutplanner.entity.User;
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

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );

    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado.")
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void update(Long id, User user) {

        User userEntity = findById(id);

        if (user.getName() != null) userEntity.setName(user.getName());
        if (user.getEmail() != null) userEntity.setEmail(user.getEmail());

        userRepository.save(userEntity);
    }

    public void deleteById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        userRepository.deleteById(id);
    }

}
