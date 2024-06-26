package com.example.springBoot.controller;

import com.example.springBoot.model.Course;
import com.example.springBoot.model.dto.request.CourseRequest;
import com.example.springBoot.model.dto.response.CourseResponse;
import com.example.springBoot.service.CourseServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/courses")
public class CourseController {
    CourseServiceImpl courseService;

    @PostMapping
    public CourseResponse save(@RequestBody CourseRequest request) {
        return courseService.save(request);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable("id") Long id, @RequestBody CourseRequest request) {
        return courseService.update(id,request);
    }

    @GetMapping("/{id}")
    public CourseResponse findById(@PathVariable("id") Long id) {
        return courseService.findById(id);
    }

    @GetMapping
    public List<CourseResponse> findAll() {
        return courseService.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        return courseService.delete(id);
    }
}
