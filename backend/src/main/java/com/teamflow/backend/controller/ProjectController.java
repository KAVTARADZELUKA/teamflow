package com.teamflow.backend.controller;

import com.teamflow.backend.dto.project.CreateProjectDTO;
import com.teamflow.backend.dto.project.ProjectResponseDTO;
import com.teamflow.backend.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody CreateProjectDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(dto));
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsByWorkspace(
            @PathVariable String workspaceId) {
        return ResponseEntity
                .ok(projectService.listProjectsByWorkspace(workspaceId));
    }
}
