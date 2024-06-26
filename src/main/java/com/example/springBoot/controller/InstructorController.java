package com.example.springBoot.controller;

import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.InstructorRequest;
import com.example.springBoot.model.dto.request.InstructorUpdateRequest;
import com.example.springBoot.model.dto.response.InstructorResponse;
import com.example.springBoot.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/{instructorId}/students")
    public List<User> getStudentsOfInstructor(@PathVariable("instructorId") Long instructorId) {
        return instructorService.getStudentsOfInstructor(instructorId);
    }

    @GetMapping("/{instructorId}/students/count")
    public int getStudentCountOfInstructor(@PathVariable("instructorId") Long instructorId) {
        return instructorService.countStudentsOfInstructor(instructorId);
    }

    @PostMapping
    public InstructorResponse save(@RequestBody InstructorRequest request) {
        return instructorService.save(request);
    }

    @PutMapping("/{id}")
    public InstructorResponse update(@PathVariable("id") Long id, @RequestBody InstructorUpdateRequest request) {
        return instructorService.update(id, request);
    }

    @GetMapping("/{id}")
    public InstructorResponse findById(@PathVariable("id") Long id) {
        return instructorService.findById(id);
    }

    @GetMapping
    public List<InstructorResponse> findAll() {
        return instructorService.findAllInstructors();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return instructorService.delete(id);
    }


}
