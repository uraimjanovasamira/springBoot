package com.example.springBoot.model.dto.response;

import com.example.springBoot.model.Course;
import com.example.springBoot.model.enums.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InstructorResponse {
    Long id;
    String name;
    String email;
    String lastName;
    Role role;
    Course course;
}
