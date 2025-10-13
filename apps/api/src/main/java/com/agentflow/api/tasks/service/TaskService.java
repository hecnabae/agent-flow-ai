package com.agentflow.api.tasks.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.agentflow.api.tasks.domain.SubtaskDocument;
import com.agentflow.api.tasks.domain.SubtaskRepository;
import com.agentflow.api.tasks.domain.TaskDocument;
import com.agentflow.api.tasks.domain.TaskRepository;
import com.agentflow.api.tasks.dto.CreateSubtaskRequest;
import com.agentflow.api.tasks.dto.CreateTaskRequest;
import com.agentflow.api.tasks.dto.SubtaskResponse;
import com.agentflow.api.tasks.dto.TaskResponse;
import com.agentflow.api.tasks.dto.UpdateSubtaskStatusRequest;
import com.agentflow.api.tasks.dto.UpdateTaskRequest;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SubtaskRepository subtaskRepository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository taskRepository, SubtaskRepository subtaskRepository, TaskMapper mapper) {
        this.taskRepository = taskRepository;
        this.subtaskRepository = subtaskRepository;
        this.mapper = mapper;
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        TaskDocument document = new TaskDocument();
        document.setProjectId(request.projectId());
        document.setTitle(request.title());
        document.setDescription(request.description());
        document.setLabels(request.labels());
        document.setAssigneeId(request.assigneeId());
        if (request.status() != null) {
            document.setStatus(request.status());
        }
        document.setCreatedAt(Instant.now());
        document.setUpdatedAt(Instant.now());
        TaskDocument saved = taskRepository.save(document);
        return mapper.toTaskResponse(saved, List.of());
    }

    public List<TaskResponse> listTasks(Optional<String> projectId) {
        List<TaskDocument> tasks = projectId
                .map(taskRepository::findByProjectId)
                .orElseGet(taskRepository::findAll);
        return tasks.stream()
                .map(task -> mapper.toTaskResponse(task, subtaskRepository.findByTaskIdOrderByOrderAsc(task.getId())))
                .toList();
    }

    public TaskResponse getTask(String id) {
        TaskDocument task = taskRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Task not found", 1));
        List<SubtaskDocument> subtasks = subtaskRepository.findByTaskIdOrderByOrderAsc(task.getId());
        return mapper.toTaskResponse(task, subtasks);
    }

    public TaskResponse updateTask(String id, UpdateTaskRequest request) {
        TaskDocument task = taskRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException("Task not found", 1));
        if (request.title() != null) {
            task.setTitle(request.title());
        }
        if (request.description() != null) {
            task.setDescription(request.description());
        }
        if (request.status() != null) {
            task.setStatus(request.status());
        }
        if (request.labels() != null) {
            task.setLabels(request.labels());
        }
        if (request.assigneeId() != null) {
            task.setAssigneeId(request.assigneeId());
        }
        task.setUpdatedAt(Instant.now());
        TaskDocument saved = taskRepository.save(task);
        List<SubtaskDocument> subtasks = subtaskRepository.findByTaskIdOrderByOrderAsc(saved.getId());
        return mapper.toTaskResponse(saved, subtasks);
    }

    public SubtaskResponse createSubtask(String taskId, CreateSubtaskRequest request) {
        verifyTaskExists(taskId);
        SubtaskDocument document = new SubtaskDocument();
        document.setTaskId(taskId);
        document.setTitle(request.title());
        document.setDescription(request.description());
        document.setEstimateMin(request.estimateMin());
        document.setDependsOn(request.dependsOn());
        document.setAcceptanceCriteria(request.acceptanceCriteria());
        document.setOrder(request.order());
        if (request.status() != null) {
            document.setStatus(request.status());
        }
        SubtaskDocument saved = subtaskRepository.save(document);
        return mapper.toSubtaskResponse(saved);
    }

    public SubtaskResponse updateSubtaskStatus(String subtaskId, UpdateSubtaskStatusRequest request) {
        SubtaskDocument subtask = subtaskRepository.findById(subtaskId)
                .orElseThrow(() -> new EmptyResultDataAccessException("Subtask not found", 1));
        subtask.setStatus(request.status());
        SubtaskDocument saved = subtaskRepository.save(subtask);
        return mapper.toSubtaskResponse(saved);
    }

    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new EmptyResultDataAccessException("Task not found", 1);
        }
        subtaskRepository.deleteAll(subtaskRepository.findByTaskIdOrderByOrderAsc(id));
        taskRepository.deleteById(id);
    }

    private void verifyTaskExists(String taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EmptyResultDataAccessException("Task not found", 1);
        }
    }
}
