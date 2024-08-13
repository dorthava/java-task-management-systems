package com.effective.mobile.tasks.service;

import com.effective.mobile.tasks.dto.*;
import com.effective.mobile.tasks.models.*;
import com.effective.mobile.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail(username);
        User assignee = userService.findByUsername(createTaskRequest.getAssignee());
        Task task = Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .status(Status.valueOf(createTaskRequest.getStatus()))
                .priority(Priority.valueOf(createTaskRequest.getPriority()))
                .author(user)
                .assignee(assignee)
                .build();
        taskRepository.save(task);
        return taskResponseTransformation(task);
    }

    @Override
    public TaskResponse updateTask(UpdateTaskRequest updateTaskRequest, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByEmail(username);
        if(!taskRepository.existsByIdAndAuthor(updateTaskRequest.getId(), user)) {
            throw new NoSuchElementException("Task with id " + updateTaskRequest.getId() + " does not exist by this author");
        }

        Long id = updateTaskRequest.getId();
        if(updateTaskRequest.getTitle() != null) {
            taskRepository.updateTitleWhereId(updateTaskRequest.getTitle(), id);
        }

        if(updateTaskRequest.getDescription() != null) {
            taskRepository.updateDescriptionWhereId(updateTaskRequest.getDescription(), id);
        }

        if(updateTaskRequest.getStatus() != null) {
            taskRepository.updateStatusWhereId(Status.valueOf(updateTaskRequest.getStatus()), id);
        }

        if(updateTaskRequest.getPriority() != null) {
            taskRepository.updatePriorityWhereId(Priority.valueOf(updateTaskRequest.getPriority()), id);
        }

        if(updateTaskRequest.getAssignee() != null) {
            taskRepository.updateAssigneeWhereId(updateTaskRequest.getAssignee(), id);
        }

        Task task = taskRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return taskResponseTransformation(task);
    }

    @Override
    public TaskResponse updateStatusTask(Long taskId, UpdateStatusTaskRequest updateStatusTaskRequest, Authentication authentication) {
        Task task = taskRepository.findById(taskId).orElseThrow(NoSuchElementException::new);
        User user = userService.findByEmail(authentication.getName());
        if(!user.getTasksAssigned().contains(task) && !user.getTasksCreated().contains(task)) {
            throw new NoSuchElementException("Данный пользователь не может менять статус выбранной задачи.");
        }
        taskRepository.updateStatusWhereId(Status.valueOf(updateStatusTaskRequest.getStatus()), task.getId());
        task.setStatus(Status.valueOf(updateStatusTaskRequest.getStatus()));
        return taskResponseTransformation(task);
    }

    @Override
    public Set<TaskResponse> findByAssignee(FindTaskRequest findTaskRequest) {
        User assignee = userService.findByUsername(findTaskRequest.getAssignee());
        Set<TaskResponse> taskResponseSet = new HashSet<>();
        for(Task task : assignee.getTasksAssigned()) {
            taskResponseSet.add(taskResponseTransformation(task));
        }
        return taskResponseSet;
    }

    @Override
    public TaskResponse deleteTask(DeleteTaskRequest deleteTaskRequest, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        Optional<Task> optionalTask = taskRepository.findById(deleteTaskRequest.getId());
        if(optionalTask.isEmpty()) {
            throw new NoSuchElementException("Задачи под id: " + deleteTaskRequest.getId() + " не существует.");
        }
        int count = taskRepository.deleteByIdAndAuthor(deleteTaskRequest.getId(), user);
        if(count == 0) { throw new NoSuchElementException("Невозможно удалить, так как пользователь не является автором задачи."); }
        return taskResponseTransformation(optionalTask.get());
    }

    private TaskResponse taskResponseTransformation(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .priority(task.getPriority().name())
                .assignee(task.getAssignee().getUsername())
                .author(task.getAuthor().getUsername())
                .build();
    }
}
