package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на обновление")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {
    @Schema(description = "id задачи", example = "12")
    @NotNull(message = "id не может быть пустым")
    @Positive
    private Long id;

    @Schema(description = "Оригинальный заголовок", example = "Помыться с мылом")
    @Size(min = 1, max = 50, message = "Описание должно быть от 1 до 50 символов")
    private String title;

    @Schema(description = "Описание", example = "Помыться с мылом")
    @Size(min = 1, max = 255, message = "Описание должно быть от 1 до 255 символов")
    private String description;

    @Schema(description = "Статус", example = "IN_PROGRESS")
    @Size(min = 1, max = 25, message = "Статус должно содержать от 1 до 25 символов")
    private String status;

    @Schema(description = "Приоритет", example = "LOW")
    @Size(min = 1, max = 25, message = "Приоритет содержит от 1 до 25 символов")
    private String priority;

    @Schema(description = "Имя исполнителя", example = "John")
    @Size(min = 1, max = 50, message = "Имя исполнителя содержит от 1 до 50 символов")
    private String assignee;
}
