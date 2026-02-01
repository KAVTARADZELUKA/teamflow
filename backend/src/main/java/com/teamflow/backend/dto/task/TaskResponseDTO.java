package com.teamflow.backend.dto.task;

import jakarta.validation.constraints.NotBlank;

public record TaskResponseDTO(
        @NotBlank
        String id,

        @NotBlank
        String projectId,

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotBlank
        String assigneeId,

        @NotBlank
        String status,

        @NotBlank
        String priority,

        @NotBlank
        String dueDate
) {
}
