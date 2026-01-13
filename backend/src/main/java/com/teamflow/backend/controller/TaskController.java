package com.teamflow.backend.controller;

import com.teamflow.backend.dto.task.AssignTaskDTO;
import com.teamflow.backend.dto.task.CreateTaskDTO;
import com.teamflow.backend.dto.task.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody CreateTaskDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(dto));
    }

    @PostMapping("/assign")
    public ResponseEntity<Void> assignTask(@RequestBody AssignTaskDTO dto) {
        taskService.assignTask(dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> changeStatus(@PathVariable String id, @RequestParam String status) {
        taskService.changeTaskStatus(id, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByProject(@PathVariable String projectId) {
        return ResponseEntity.ok(
                taskService.listTasksByProject(projectId)
        );
    }
}
