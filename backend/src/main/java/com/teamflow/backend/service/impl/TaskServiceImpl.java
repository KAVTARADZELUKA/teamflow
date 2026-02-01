package com.teamflow.backend.service.impl;

import com.teamflow.backend.dto.task.AssignTaskDTO;
import com.teamflow.backend.dto.task.CreateTaskDTO;
import com.teamflow.backend.dto.task.TaskResponseDTO;
import com.teamflow.backend.exception.BadRequestException;
import com.teamflow.backend.exception.ResourceNotFoundException;
import com.teamflow.backend.mapper.TaskMapper;
import com.teamflow.backend.model.Project;
import com.teamflow.backend.model.Task;
import com.teamflow.backend.model.User;
import com.teamflow.backend.model.enums.TaskPriority;
import com.teamflow.backend.model.enums.TaskStatus;
import com.teamflow.backend.repository.ProjectRepository;
import com.teamflow.backend.repository.TaskRepository;
import com.teamflow.backend.repository.TeamRepository;
import com.teamflow.backend.repository.UserRepository;
import com.teamflow.backend.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    @Override
    public TaskResponseDTO createTask(CreateTaskDTO dto) {
        if (dto.title() == null || dto.title().isBlank()) {
            throw new BadRequestException("Task title must not be empty");
        }

        Project project = projectRepository.findById(Long.parseLong(dto.projectId()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        User assignee = null;
        if (dto.assigneeId() != null) {
            assignee = userRepository.findById(Long.parseLong(dto.assigneeId()))
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Assignee user not found"));
        }

        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setProject(project);
        task.setAssigneeAt(assignee);
        task.setStatus(TaskStatus.TODO); // უკეთესი იქნება Enum
        task.setPriority(dto.priority() != null ? TaskPriority.valueOf(dto.priority()) : TaskPriority.MEDIUM);
        task.setDueDate(
                dto.dueDate() != null ? LocalDate.parse(dto.dueDate()) : null
        );

        taskRepository.save(task);

        return TaskMapper.toResponse(task);
    }

    @Override
    public void assignTask(AssignTaskDTO dto) {
        Task task = taskRepository.findById(Long.parseLong(dto.taskId()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        User user = userRepository.findById(Long.parseLong(dto.userId()))
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Project project = task.getProject();
        Long workspaceId = project.getWorkspace().getId();

        boolean isMember = teamRepository
                .existsByWorkspaceIdAndMembersId(workspaceId, user.getId());

        if (!isMember) {
            throw new BadRequestException(
                    "User is not a member of this workspace");
        }

        if (task.getAssigneeAt() != null &&
                task.getAssigneeAt().getId().equals(user.getId())) {
            throw new BadRequestException(
                    "Task already assigned to this user");
        }

        task.setAssigneeAt(user);
        taskRepository.save(task);
    }

    @Override
    public void changeTaskStatus(String taskId, String status) {
        Task task = taskRepository.findById(Long.parseLong(taskId))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        if (!List.of("TODO", "IN_PROGRESS", "REVIEW", "DONE")
                .contains(status)) {
            throw new BadRequestException("Invalid task status");
        }

        task.setStatus(TaskStatus.valueOf(status));
        taskRepository.save(task);
    }

    @Override
    public List<TaskResponseDTO> listTasksByProject(String projectId) {
        Long pid;
        try {
            pid = Long.parseLong(projectId);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid project id");
        }

        if (!projectRepository.existsById(pid)) {
            throw new ResourceNotFoundException("Project not found");
        }

        return taskRepository.findAllByProjectId(pid)
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }
}
