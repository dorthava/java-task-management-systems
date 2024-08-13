package com.effective.mobile.tasks.service;

import com.effective.mobile.tasks.dto.*;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest createTaskRequest, Authentication authentication);
    TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, Authentication authentication);
    TaskResponse updateStatusTask(Long taskId, UpdateStatusTaskRequest updateStatusTaskRequest, Authentication authentication);
    Set<TaskResponse> findByAssignee(FindTaskRequest findTaskRequest);
    TaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest, Authentication authentication);
}
