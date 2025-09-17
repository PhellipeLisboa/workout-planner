package com.phellipe.workoutplanner.service;

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

    public void save(User user) {
        userRepository.save(user);
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
