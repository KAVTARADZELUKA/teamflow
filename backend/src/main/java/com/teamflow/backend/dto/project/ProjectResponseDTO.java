package com.teamflow.backend.dto.project;

import jakarta.validation.constraints.NotBlank;

public record ProjectResponseDTO(
        @NotBlank
        String id,

        @NotBlank
        String workspaceId,

        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        String status,

        @NotBlank
        String startDate,

        @NotBlank
        String endDate
) {
}
