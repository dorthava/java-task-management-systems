package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Schema(description = "Запрос на создание задачи")
@AllArgsConstructor
public class CreateTaskRequest {
    @Schema(description = "Заголовок", example = "Помыться")
    @Size(min = 1, max = 50, message = "Заголовок должен содержать от 1 до 50 символов")
    @NotBlank(message = "Заголовок не должен быть пустым")
    private String title;

    @Schema(description = "Описание", example = "Помыться с мылом")
    @Size(min = 1, max = 255, message = "Описание должно быть от 1 до 255 символов")
    @NotBlank(message = "Описание не может быть пустым")
    private String description;

    @Schema(description = "Статус", example = "IN_PROGRESS")
    @Size(min = 1, max = 25, message = "Статус должно содержать от 1 до 25 символов")
    @NotBlank(message = "Статус не может быть пустым")
    private String status;

    @Schema(description = "Приоритет", example = "LOW")
    @Size(min = 1, max = 25, message = "Приоритет содержит от 1 до 25 символов")
    @NotBlank(message = "Приоритет не может быть пустым")
    private String priority;

    @Schema(description = "Имя исполнителя", example = "John")
    @Size(min = 1, max = 50, message = "Имя исполнителя содержит от 1 до 50 символов")
    @NotBlank(message = "Имя исполнителя не может быть пустым")
    private String assignee;
}
