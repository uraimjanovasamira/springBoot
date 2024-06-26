package com.example.springBoot.mapper;

import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.UserRequest;
import com.example.springBoot.model.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User userMapper(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setStudyFormat(request.getStudyFormat());
        return user;
    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .studyFormat(user.getStudyFormat())
                .build();
    }


}
