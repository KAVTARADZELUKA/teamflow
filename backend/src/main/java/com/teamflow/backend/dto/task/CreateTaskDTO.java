package com.teamflow.backend.dto.task;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskDTO(
        @NotBlank
        String projectId,

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotBlank
        String assigneeId,

        @NotBlank
        String priority,

        @NotBlank
        String dueDate
) {
}
