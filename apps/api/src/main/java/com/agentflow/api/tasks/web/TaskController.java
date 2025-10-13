package com.agentflow.api.tasks.web;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agentflow.api.tasks.dto.CreateSubtaskRequest;
import com.agentflow.api.tasks.dto.CreateTaskRequest;
import com.agentflow.api.tasks.dto.SubtaskResponse;
import com.agentflow.api.tasks.dto.TaskResponse;
import com.agentflow.api.tasks.dto.UpdateTaskRequest;
import com.agentflow.api.tasks.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> listTasks(@RequestParam(name = "projectId", required = false) Optional<String> projectId) {
        return taskService.listTasks(projectId);
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(@PathVariable String id) {
        return taskService.getTask(id);
    }

    @PostMapping("/{id}/subtasks")
    @ResponseStatus(HttpStatus.CREATED)
    public SubtaskResponse createSubtask(@PathVariable String id, @Valid @RequestBody CreateSubtaskRequest request) {
        return taskService.createSubtask(id, request);
    }

    @PatchMapping("/{id}")
    public TaskResponse updateTask(@PathVariable String id, @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
