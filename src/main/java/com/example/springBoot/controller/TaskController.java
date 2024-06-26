package com.example.springBoot.controller;

import com.example.springBoot.model.Task;
import com.example.springBoot.model.dto.request.TaskRequest;
import com.example.springBoot.model.dto.response.TaskResponse;
import com.example.springBoot.service.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/tasks")
public class TaskController {

    TaskService taskService;

    @GetMapping("/{taskId}/tasks")
    public Long getTasksByInstructor(@PathVariable Long taskId) {
        return taskService.getCountTasksByInstructor(taskId);
    }

    @PostMapping
    public TaskResponse save(@RequestBody TaskRequest request) {
        return taskService.save(request);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable("id") Long id, @RequestBody TaskRequest request) {
        return taskService.update(id, request);
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable("id") Long id) {
        return taskService.findById(id);
    }

    @GetMapping
    public List<TaskResponse> findAll() {
        return taskService.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        return taskService.delete(id);
    }
}

