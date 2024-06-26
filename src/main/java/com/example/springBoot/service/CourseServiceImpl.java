package com.example.springBoot.service;

import com.example.springBoot.mapper.CourseMapper;
import com.example.springBoot.model.Company;
import com.example.springBoot.model.Course;
import com.example.springBoot.model.dto.request.CourseRequest;
import com.example.springBoot.model.dto.response.CourseResponse;
import com.example.springBoot.repository.CompanyRepository;
import com.example.springBoot.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CompanyRepository companyRepository;

    public CourseResponse save(CourseRequest request) {
        Course course = courseMapper.courseMapper(request);
        Company company = companyRepository.findById(request.getCompany_id()).get();
        course.setCompany(company);
        courseRepository.save(course);
        return courseMapper.mapToResponse(course);
    }

    public List<CourseResponse> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::mapToResponse).toList();
    }

    public CourseResponse findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Course by id" + id));
        return courseMapper.mapToResponse(course);
    }

    public Course update(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Course by id" + id));
        course.setCourseName(request.getCourseName());
        course.setDuration(request.getDuration());
        log.info("Successfully updated Course with id:{}", id);
        return courseRepository.save(course);
    }

    public String delete(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Course by id" + id));
        courseRepository.delete(course);
        return "Successfully deleted course by id" + id;
    }
}
