package com.example.springBoot.model.dto.request;

import com.example.springBoot.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequest {
    Long id;
    String taskName;
    int dueDate;
    User instructor_id;

}
