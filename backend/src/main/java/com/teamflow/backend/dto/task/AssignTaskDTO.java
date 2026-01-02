package com.teamflow.backend.dto.task;

import jakarta.validation.constraints.NotBlank;

public record AssignTaskDTO(
        @NotBlank
        String taskId,

        @NotBlank
        String userId
) {
}
