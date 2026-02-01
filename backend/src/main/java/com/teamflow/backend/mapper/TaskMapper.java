package com.teamflow.backend.mapper;

import com.teamflow.backend.dto.task.TaskResponseDTO;
import com.teamflow.backend.model.Task;

public class TaskMapper {
    public static TaskResponseDTO toResponse(Task task) {
        return new TaskResponseDTO(
                task.getId().toString(),
                task.getProject().getId().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getAssigneeAt() != null
                        ? task.getAssigneeAt().getId().toString()
                        : null,
                task.getStatus().toString(),
                task.getPriority().toString(),
                task.getDueDate().toString()
        );
    }
}
