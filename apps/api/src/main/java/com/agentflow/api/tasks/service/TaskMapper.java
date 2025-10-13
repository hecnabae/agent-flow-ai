package com.agentflow.api.tasks.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.agentflow.api.tasks.domain.SubtaskDocument;
import com.agentflow.api.tasks.domain.TaskDocument;
import com.agentflow.api.tasks.dto.SubtaskResponse;
import com.agentflow.api.tasks.dto.TaskResponse;

@Component
class TaskMapper {

    TaskResponse toTaskResponse(TaskDocument document, List<SubtaskDocument> subtasks) {
        return new TaskResponse(
                document.getId(),
                document.getProjectId(),
                document.getTitle(),
                document.getDescription(),
                document.getStatus(),
                safeSet(document.getLabels()),
                document.getAssigneeId(),
                document.getCreatedAt(),
                document.getUpdatedAt(),
                subtasks.stream().map(this::toSubtaskResponse).toList()
        );
    }

    SubtaskResponse toSubtaskResponse(SubtaskDocument document) {
        return new SubtaskResponse(
                document.getId(),
                document.getTaskId(),
                document.getTitle(),
                document.getDescription(),
                document.getEstimateMin(),
                document.getDependsOn(),
                document.getStatus(),
                document.getAcceptanceCriteria(),
                document.getOrder()
        );
    }

    private Set<String> safeSet(Set<String> labels) {
        return labels == null ? Set.of() : Set.copyOf(labels);
    }
}
