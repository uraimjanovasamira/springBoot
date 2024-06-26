package com.example.springBoot.model.dto.request;

import com.example.springBoot.model.Course;
import com.example.springBoot.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonFormat
public class InstructorUpdateRequest {
    String name;
    String lastName;
    String email;
    Role role;
    Course course;
}
