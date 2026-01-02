package com.teamflow.backend.dto.project;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectDTO(
        @NotBlank
        String workspaceId,

        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        String startDate,

        @NotBlank
        String endDate
) {
}
