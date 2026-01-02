package com.teamflow.backend.dto.workspace;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWorkspaceDTO(
        @NotBlank @Size(min = 6, max = 100)
        String name,

        @NotBlank
        Long ownerId
) {
}
