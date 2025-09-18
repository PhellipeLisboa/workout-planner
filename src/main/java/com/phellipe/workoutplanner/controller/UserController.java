package com.phellipe.workoutplanner.controller;

import com.phellipe.workoutplanner.dto.UserRequestDto;
import com.phellipe.workoutplanner.dto.UserResponseDto;
import com.phellipe.workoutplanner.dto.UserUpdateDto;
import com.phellipe.workoutplanner.entity.User;
import com.phellipe.workoutplanner.mapper.UserMapper;
import com.phellipe.workoutplanner.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserRequestDto dto) {
        UserResponseDto savedUser = userService.save(dto);
        URI location = URI.create("/users/" + savedUser.id());
        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll()
    {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        UserResponseDto updatedUser = userService.update(id, dto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
