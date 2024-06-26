package com.example.springBoot.model.dto.request;

import com.example.springBoot.model.Course;
import com.example.springBoot.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupRequest {

    Long id;
    String groupName;
    LocalDate dateOfStart;
    LocalDate dateOfFinish;
    List<Course> courses;
    List<User>users;
    Long courseId;
}
