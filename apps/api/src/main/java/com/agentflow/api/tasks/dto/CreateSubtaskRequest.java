package com.agentflow.api.tasks.dto;

import java.util.List;

import com.agentflow.api.tasks.domain.SubtaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateSubtaskRequest(
        @NotBlank String title,
        @Size(max = 10_000) String description,
        @Positive Integer estimateMin,
        List<String> dependsOn,
        List<String> acceptanceCriteria,
        Integer order,
        SubtaskStatus status
) {
}
