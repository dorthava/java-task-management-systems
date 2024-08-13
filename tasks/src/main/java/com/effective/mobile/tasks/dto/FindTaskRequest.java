package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на нахождение всех выполняемых задач пользователем")
@AllArgsConstructor
@NoArgsConstructor
public class FindTaskRequest {
    @Schema(description = "Исполнитель", example = "John")
    @Size(min = 1, max = 255, message = "Имя пользователя должно содержать от 1 до 50 символов")
    @NotBlank(message = "Имя не может быть пустым")
    private String assignee;
}
