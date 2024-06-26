package com.example.springBoot.model.dto.response;

import com.example.springBoot.model.Course;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupResponse {
    Long id;
    String groupName;
    LocalDate dateOfStart;
    LocalDate dateOfFinish;
    Long courseId;

}
