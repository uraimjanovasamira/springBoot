package com.example.springBoot.mapper;

import com.example.springBoot.model.Task;
import com.example.springBoot.model.dto.request.TaskRequest;
import com.example.springBoot.model.dto.response.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task taskMapper(TaskRequest request) {
        Task task = new Task();
        task.setTaskName(request.getTaskName());
        task.setDueDate(request.getDueDate());
        task.setInstructor(request.getInstructor_id());
        return task;
    }


    public TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .taskName(task.getTaskName())
                .dueDate(task.getDueDate())
                .build();
    }
}
