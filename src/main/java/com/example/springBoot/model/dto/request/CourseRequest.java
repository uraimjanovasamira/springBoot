package com.example.springBoot.model.dto.request;

import com.example.springBoot.model.Company;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {
    String courseName;
    String duration;
    Long company_id;

}
