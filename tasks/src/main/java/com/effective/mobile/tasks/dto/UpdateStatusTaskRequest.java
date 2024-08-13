package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на обновление статуса")
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusTaskRequest {
    @Schema(description = "Статус", example = "IN_PROGRESS")
    @Size(min = 1, max = 25, message = "Статус должно содержать от 1 до 25 символов")
    private String status;

}
