package com.example.springBoot.model.dto.request;

import com.example.springBoot.model.enums.Role;
import com.example.springBoot.model.enums.StudyFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String name;
    String lastname;
    String email;
    String password;
    Role role;
    StudyFormat studyFormat;
    Long course_id;
    Long group_id;
}
