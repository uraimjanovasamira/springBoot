package com.example.springBoot.controller;

import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.LoginRequest;
import com.example.springBoot.model.dto.request.UserRequest;
import com.example.springBoot.model.dto.request.UserUpdateRequest;
import com.example.springBoot.model.dto.response.LoginResponse;
import com.example.springBoot.model.dto.response.UserResponse;
import com.example.springBoot.repository.UserRepository;
import com.example.springBoot.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/users")
public class UserController {
    UserService userService;
    UserRepository userRepository;

    @GetMapping("/search")
    public User searchUserByName(@RequestParam String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("User not found with name: " + name));
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping
    public UserResponse save(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequest request) {
        return userService.update(id, request);
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }


}
