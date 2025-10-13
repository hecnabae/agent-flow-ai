package com.agentflow.api.tasks.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agentflow.api.tasks.dto.SubtaskResponse;
import com.agentflow.api.tasks.dto.UpdateSubtaskStatusRequest;
import com.agentflow.api.tasks.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subtasks")
@Validated
public class SubtaskController {

    private final TaskService taskService;

    public SubtaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PatchMapping("/{id}")
    public SubtaskResponse updateStatus(@PathVariable String id, @Valid @RequestBody UpdateSubtaskStatusRequest request) {
        return taskService.updateSubtaskStatus(id, request);
    }
}
