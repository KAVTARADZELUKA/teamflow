package com.teamflow.backend.mapper;

import com.teamflow.backend.dto.workspace.WorkspaceResponseDTO;
import com.teamflow.backend.model.Workspace;

public class WorkspaceMapper {
    public static WorkspaceResponseDTO toResponse(Workspace workspace) {
        return new WorkspaceResponseDTO(
                workspace.getId().toString(),
                workspace.getName(),
                workspace.getOwner().getId().toString(),
                workspace.getCreatedAt() != null
                        ? workspace.getCreatedAt().toString()
                        : null
        );
    }
}
