package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Ответ при успешном создании")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    @Schema(description = "id созданной задачи", example = "12")
    private Long id;

    @Schema(description = "Название созданной задачи", example = "Заказ №13")
    private String title;

    @Schema(description = "Описание", example = "Помыться с мылом")
    private String description;

    @Schema(description = "Статус", example = "IN_PROGRESS")
    private String status;

    @Schema(description = "Приоритет", example = "LOW")
    private String priority;

    @Schema(description = "Имя автора", example = "Adel")
    private String author;

    @Schema(description = "Имя исполнителя", example = "John")
    private String assignee;
}
