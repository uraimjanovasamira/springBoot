package com.example.springBoot.model.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class TaskResponse {
    Long id;
    String taskName;
    int dueDate;


}
