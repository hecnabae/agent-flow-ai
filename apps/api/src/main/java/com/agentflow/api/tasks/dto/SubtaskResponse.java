package com.agentflow.api.tasks.dto;

import java.util.List;

import com.agentflow.api.tasks.domain.SubtaskStatus;

public record SubtaskResponse(
        String id,
        String taskId,
        String title,
        String description,
        Integer estimateMin,
        List<String> dependsOn,
        SubtaskStatus status,
        List<String> acceptanceCriteria,
        Integer order
) {
}
