package com.teamflow.backend.mapper;

import com.teamflow.backend.dto.project.ProjectResponseDTO;
import com.teamflow.backend.model.Project;

public class ProjectMapper {
    public static ProjectResponseDTO toResponse(Project project) {
        return new ProjectResponseDTO(
                project.getId().toString(),
                project.getWorkspace().getId().toString(),
                project.getName(),
                project.getDescription(),
                project.getStatus().toString(),
                project.getStartDate().toString(),
                project.getEndDate().toString()
        );
    }
}
