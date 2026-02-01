package com.teamflow.backend.service;

import com.teamflow.backend.dto.workspace.CreateWorkspaceDTO;
import com.teamflow.backend.dto.workspace.WorkspaceResponseDTO;

public interface WorkspaceService {
    WorkspaceResponseDTO createWorkspace(CreateWorkspaceDTO dto);
    WorkspaceResponseDTO getWorkspaceById(String id);
}
