package com.agentflow.api.tasks.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agentflow.api.tasks.domain.SubtaskRepository;
import com.agentflow.api.tasks.domain.TaskDocument;
import com.agentflow.api.tasks.domain.TaskRepository;
import com.agentflow.api.tasks.domain.TaskStatus;
import com.agentflow.api.tasks.dto.CreateTaskRequest;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private SubtaskRepository subtaskRepository;

    @Mock
    private TaskMapper mapper;

    @InjectMocks
    private TaskService taskService;

    private TaskDocument persisted;

    @BeforeEach
    void setUp() {
        persisted = new TaskDocument();
        persisted.setId("task-1");
        persisted.setProjectId("project-1");
        persisted.setTitle("Implementar endpoint");
        persisted.setStatus(TaskStatus.TODO);
    }

    @Test
    void createTaskPersistsAndReturnsResponse() {
        CreateTaskRequest request = new CreateTaskRequest("project-1", "Implementar endpoint", null, null, null, null);
        given(taskRepository.save(any(TaskDocument.class))).willReturn(persisted);
        given(mapper.toTaskResponse(eq(persisted), eq(List.of()))).willReturn(null);

        taskService.createTask(request);

        verify(taskRepository).save(any(TaskDocument.class));
        verify(mapper).toTaskResponse(persisted, List.of());
    }

    @Test
    void listTasksLoadsSubtasks() {
        given(taskRepository.findAll()).willReturn(List.of(persisted));
        given(subtaskRepository.findByTaskIdOrderByOrderAsc("task-1")).willReturn(List.of());
        given(mapper.toTaskResponse(eq(persisted), eq(List.of()))).willReturn(null);

        taskService.listTasks(Optional.empty());

        verify(taskRepository).findAll();
        verify(subtaskRepository).findByTaskIdOrderByOrderAsc("task-1");
        verify(mapper).toTaskResponse(persisted, List.of());
    }
}
