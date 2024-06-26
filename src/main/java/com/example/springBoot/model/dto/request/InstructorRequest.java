package com.example.springBoot.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonFormat
public class InstructorRequest {
    String name;
    String email;
    String lastName;
    String password;
    Long course_id;
}



