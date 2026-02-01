package com.teamflow.backend.dto.workspace;

import jakarta.validation.constraints.NotBlank;

public record WorkspaceResponseDTO(
        @NotBlank
        String id,

        @NotBlank
        String name,

        @NotBlank
        String ownerId,

        @NotBlank
        String createdAt
) {
}
