package com.example.springBoot.service;

import com.example.springBoot.mapper.TaskMapper;
import com.example.springBoot.model.Task;
import com.example.springBoot.model.User;
import com.example.springBoot.model.dto.request.TaskRequest;
import com.example.springBoot.model.dto.response.TaskResponse;
import com.example.springBoot.repository.TaskRepository;
import com.example.springBoot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {
    TaskMapper taskMapper;
    UserRepository userRepository;
    TaskRepository taskRepository;

    public Long getCountTasksByInstructor(Long taskId) {
        return taskRepository.countTasksByInstructor(taskId);
    }

    public TaskResponse save(TaskRequest request) {
        Task task = taskMapper.taskMapper(request);
        User user = userRepository.findById(request.getId()).get();
        task.setInstructor(user);
        taskRepository.save(task);
        return taskMapper.mapToResponse(task);
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToResponse).toList();
    }

    public TaskResponse findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found task by id" + id));
        return taskMapper.mapToResponse(task);
    }

    public Task update(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found task by id" + id));
        task.setTaskName(request.getTaskName());
        task.setDueDate(request.getDueDate());
        task.setInstructor(request.getInstructor_id());
        log.info("Successfully updated task with id:{}", id);
        return taskRepository.save(task);
    }

    public String delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found task by id" + id));
        taskRepository.delete(task);
        return "Successfully deleted task by id" + id;
    }
}
