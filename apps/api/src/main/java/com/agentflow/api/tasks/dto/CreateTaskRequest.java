package com.agentflow.api.tasks.dto;

import java.util.Set;

import com.agentflow.api.tasks.domain.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequest(
        @NotBlank String projectId,
        @NotBlank @Size(max = 255) String title,
        @Size(max = 10_000) String description,
        Set<String> labels,
        String assigneeId,
        TaskStatus status
) {
}
