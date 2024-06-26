package com.example.springBoot.mapper;

import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.InstructorRequest;
import com.example.springBoot.model.dto.response.InstructorResponse;
import com.example.springBoot.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {

    public User instructorMapper(InstructorRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.INSTRUCTOR);
        return user;
    }

    public InstructorResponse mapToResponse(User user) {
        return InstructorResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .course(user.getCourse())
                .build();
    }
}
