package com.teamflow.backend.service;

import com.teamflow.backend.dto.project.CreateProjectDTO;
import com.teamflow.backend.dto.project.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {
    ProjectResponseDTO createProject(CreateProjectDTO dto);
    List<ProjectResponseDTO> listProjectsByWorkspace(String workspaceId);
}
