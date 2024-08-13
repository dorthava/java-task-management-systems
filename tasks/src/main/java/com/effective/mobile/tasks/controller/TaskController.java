package com.effective.mobile.tasks.controller;

import com.effective.mobile.tasks.dto.*;
import com.effective.mobile.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public TaskResponse createTask(@RequestBody CreateTaskRequest createTaskRequest, Authentication authentication) {
        return taskService.createTask(createTaskRequest, authentication);
    }

    @PostMapping("/update")
    public TaskResponse updateTask(@RequestBody UpdateTaskRequest updateTaskRequest, Authentication authentication) {
        return taskService.updateTask(updateTaskRequest, authentication);
    }

    @PatchMapping("/update-status/{taskId}")
    public TaskResponse updateStatusTask(@PathVariable Long taskId, @RequestBody UpdateStatusTaskRequest updateStatusTaskRequest, Authentication authentication) {

        return taskService.updateStatusTask(taskId, updateStatusTaskRequest, authentication);
    }

    @GetMapping("/find")
    public Set<TaskResponse> findTask(@RequestBody FindTaskRequest findTaskRequest) {
        return taskService.findByAssignee(findTaskRequest);
    }

    @DeleteMapping("/delete")
    public TaskResponse deleteTask(@RequestBody DeleteTaskRequest deleteTaskRequest, Authentication authentication) {
        return taskService.deleteTask(deleteTaskRequest, authentication);
    }
}
