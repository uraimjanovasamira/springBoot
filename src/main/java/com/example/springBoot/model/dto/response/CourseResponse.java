package com.example.springBoot.model.dto.response;

import com.example.springBoot.model.Company;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {
    Long id;
    String courseName;
    String duration;
    Company company;

}
