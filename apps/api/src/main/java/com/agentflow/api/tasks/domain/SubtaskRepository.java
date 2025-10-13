package com.agentflow.api.tasks.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubtaskRepository extends MongoRepository<SubtaskDocument, String> {

    List<SubtaskDocument> findByTaskIdOrderByOrderAsc(String taskId);
}
