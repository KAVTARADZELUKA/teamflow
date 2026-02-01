package com.teamflow.backend.service.impl;

import com.teamflow.backend.dto.workspace.CreateWorkspaceDTO;
import com.teamflow.backend.dto.workspace.WorkspaceResponseDTO;
import com.teamflow.backend.exception.BadRequestException;
import com.teamflow.backend.exception.ResourceNotFoundException;
import com.teamflow.backend.mapper.WorkspaceMapper;
import com.teamflow.backend.model.User;
import com.teamflow.backend.model.Workspace;
import com.teamflow.backend.repository.UserRepository;
import com.teamflow.backend.repository.WorkspaceRepository;
import com.teamflow.backend.service.WorkspaceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    @Override
    public WorkspaceResponseDTO createWorkspace(CreateWorkspaceDTO dto) {
        if (dto.name() == null || dto.name().isBlank()) {
            throw new BadRequestException("Workspace name must not be empty");
        }

        User owner = userRepository.findById(dto.ownerId())
                .orElseThrow(() -> new ResourceNotFoundException("Owner user not found"));

        Workspace workspace = new Workspace();
        workspace.setName(dto.name());
        workspace.setOwner(owner);

        workspaceRepository.saveAndFlush(workspace);

        return WorkspaceMapper.toResponse(workspace);
    }

    @Override
    public WorkspaceResponseDTO getWorkspaceById(String id) {
        Workspace workspace = workspaceRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ResourceNotFoundException("Workspace not found"));

        return WorkspaceMapper.toResponse(workspace);
    }
}
