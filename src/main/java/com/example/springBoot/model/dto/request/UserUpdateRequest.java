package com.example.springBoot.model.dto.request;

import com.example.springBoot.model.enums.Role;
import com.example.springBoot.model.enums.StudyFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
public class UserUpdateRequest {
    String name;
    String lastName;
    String email;
    Role role;
    StudyFormat studyFormat;
}
