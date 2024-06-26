package com.example.springBoot.service;

import com.example.springBoot.mapper.InstructorMapper;
import com.example.springBoot.model.Course;
import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.InstructorRequest;
import com.example.springBoot.model.dto.request.InstructorUpdateRequest;
import com.example.springBoot.model.dto.response.InstructorResponse;
import com.example.springBoot.model.enums.Role;
import com.example.springBoot.repository.CourseRepository;
import com.example.springBoot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InstructorService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final InstructorMapper instructorMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> getStudentsOfInstructor(Long instructorId) {
        return userRepository.findStudentsByInstructor(instructorId);
    }

    public int countStudentsOfInstructor(Long instructorId) {
        List<User> students = getStudentsOfInstructor(instructorId);
        return students.size();
    }

    public InstructorResponse save(InstructorRequest request) {
        Course course = courseRepository.findById(request.getCourse_id())
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + request.getCourse_id()));

        User user = instructorMapper.instructorMapper(request);
        user.setRole(Role.INSTRUCTOR);
        user.setCourse(course);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        log.info("Saved new instructor: {}", savedUser);
        return instructorMapper.mapToResponse(user);
    }

    public List<InstructorResponse> findAllInstructors() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole() == Role.INSTRUCTOR)
                .map(instructorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public InstructorResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));
        return instructorMapper.mapToResponse(user);
    }

    @Transactional
    public InstructorResponse update(Long id, InstructorUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));

        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);
        log.info("Updated instructor: {}", updatedUser);
        return instructorMapper.mapToResponse(updatedUser);
    }

    @Transactional
    public String delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + id));

        userRepository.delete(user);
        log.info("Deleted instructor: {}", user);
        return "Successfully deleted Instructor by id " + id;
    }
}
