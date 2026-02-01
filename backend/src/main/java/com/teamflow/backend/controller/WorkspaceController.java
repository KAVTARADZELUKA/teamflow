package com.teamflow.backend.controller;

import com.teamflow.backend.dto.workspace.CreateWorkspaceDTO;
import com.teamflow.backend.dto.workspace.WorkspaceResponseDTO;
import com.teamflow.backend.service.WorkspaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workspaces")
@AllArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceResponseDTO> createWorkspace(@RequestBody CreateWorkspaceDTO dto) {
        WorkspaceResponseDTO response = workspaceService.createWorkspace(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceResponseDTO> getWorkspace(@PathVariable String id) {
        return ResponseEntity.ok(workspaceService.getWorkspaceById(id));
    }
}
