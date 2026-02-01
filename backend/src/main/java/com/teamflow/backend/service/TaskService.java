package com.teamflow.backend.service;

import com.teamflow.backend.dto.task.AssignTaskDTO;
import com.teamflow.backend.dto.task.CreateTaskDTO;
import com.teamflow.backend.dto.task.TaskResponseDTO;

import java.util.List;

public interface TaskService {
    TaskResponseDTO createTask(CreateTaskDTO dto);

    void assignTask(AssignTaskDTO dto);

    void changeTaskStatus(String taskId, String status);

    List<TaskResponseDTO> listTasksByProject(String projectId);
}
