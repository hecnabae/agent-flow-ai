package com.agentflow.api.tasks.dto;

import com.agentflow.api.tasks.domain.SubtaskStatus;

import jakarta.validation.constraints.NotNull;

public record UpdateSubtaskStatusRequest(@NotNull SubtaskStatus status) {
}
