package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на удаление задачи")
@NoArgsConstructor
@AllArgsConstructor
public class DeleteTaskRequest {
    @Schema(description = "id задачи", example = "12")
    @NotNull(message = "id не может быть пустым")
    @Positive
    private Long id;
}
