package com.example.springBoot.model.dto.response;

import com.example.springBoot.model.enums.Role;
import com.example.springBoot.model.enums.StudyFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class UserResponse {
    Long id;
    String name;
    String lastname;
    String email;
    Role role;
    StudyFormat studyFormat;
}
