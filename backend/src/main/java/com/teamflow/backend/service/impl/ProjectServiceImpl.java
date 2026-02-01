package com.teamflow.backend.service.impl;

import com.teamflow.backend.dto.project.CreateProjectDTO;
import com.teamflow.backend.dto.project.ProjectResponseDTO;
import com.teamflow.backend.exception.BadRequestException;
import com.teamflow.backend.exception.ResourceNotFoundException;
import com.teamflow.backend.mapper.ProjectMapper;
import com.teamflow.backend.model.Project;
import com.teamflow.backend.model.Workspace;
import com.teamflow.backend.model.enums.ProjectStatus;
import com.teamflow.backend.repository.ProjectRepository;
import com.teamflow.backend.repository.WorkspaceRepository;
import com.teamflow.backend.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public ProjectResponseDTO createProject(CreateProjectDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new BadRequestException("Project name must not be empty");
        }

        Workspace workspace = workspaceRepository.findById(Long.parseLong(dto.workspaceId()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Workspace not found"));

        if (dto.startDate() != null && dto.endDate() != null) {
            LocalDate start = LocalDate.parse(dto.startDate());
            LocalDate end = LocalDate.parse(dto.endDate());

            if (start.isAfter(end)) {
                throw new BadRequestException("Start date must be before end date");
            }
        }

        Project project = new Project();
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setWorkspace(workspace);
        project.setStatus(ProjectStatus.ACTIVE);
        project.setStartDate(
                dto.startDate() != null ? LocalDate.parse(dto.startDate()) : null
        );
        project.setEndDate(
                dto.endDate() != null ? LocalDate.parse(dto.endDate()) : null
        );

        projectRepository.save(project);

        return ProjectMapper.toResponse(project);
    }

    @Override
    public List<ProjectResponseDTO> listProjectsByWorkspace(String workspaceId) {
        Workspace workspace = workspaceRepository.findById(Long.parseLong(workspaceId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Workspace not found"));

        return projectRepository.findByWorkspace(workspace)
                .stream()
                .map(ProjectMapper::toResponse)
                .collect(Collectors.toList());
    }
}
