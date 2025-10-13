package com.agentflow.api.tasks.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<TaskDocument, String> {

    List<TaskDocument> findByProjectId(String projectId);
}
