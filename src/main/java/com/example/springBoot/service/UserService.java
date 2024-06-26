package com.example.springBoot.service;

import com.example.springBoot.mapper.UserMapper;
import com.example.springBoot.model.Group;
import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.UserRequest;
import com.example.springBoot.model.dto.request.UserUpdateRequest;
import com.example.springBoot.model.dto.response.UserResponse;
import com.example.springBoot.repository.GroupRepository;
import com.example.springBoot.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GroupRepository groupRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User searchUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("User not found with name: " + name));
    }

    public UserResponse save(UserRequest request) {
        User user = userMapper.userMapper(request);
        Group group = groupRepository.findById(request.getGroup_id())
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + request.getGroup_id()));
        user.setGroup(group);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return userMapper.mapToResponse(user);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToResponse).toList();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found User by id" + id));
        return userMapper.mapToResponse(user);
    }

    public User update(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("User not found by id:{}", id);
                    return new RuntimeException("Not found User by id" + id);
                });
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }


    public String delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found User by id" + id));
        userRepository.delete(user);
        return "Successfully deleted user by id" + id;
    }


}
