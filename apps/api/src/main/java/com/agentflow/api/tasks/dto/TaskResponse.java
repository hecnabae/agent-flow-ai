package com.agentflow.api.tasks.dto;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import com.agentflow.api.tasks.domain.TaskStatus;

public record TaskResponse(
        String id,
        String projectId,
        String title,
        String description,
        TaskStatus status,
        Set<String> labels,
        String assigneeId,
        Instant createdAt,
        Instant updatedAt,
        List<SubtaskResponse> subtasks
) {
}
