package com.agentflow.api.tasks.dto;

import java.util.Set;

import com.agentflow.api.tasks.domain.TaskStatus;

import jakarta.validation.constraints.Size;

public record UpdateTaskRequest(
        @Size(max = 255) String title,
        @Size(max = 10_000) String description,
        TaskStatus status,
        Set<String> labels,
        String assigneeId
) {
}
