package com.example.springBoot.mapper;

import com.example.springBoot.model.Course;
import com.example.springBoot.model.dto.request.CourseRequest;
import com.example.springBoot.model.dto.response.CourseResponse;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course courseMapper(CourseRequest request) {
        Course course = new Course();
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        return course;
    }


    public CourseResponse mapToResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .duration(course.getDuration())
                .company(course.getCompany())
                .build();
    }
}
