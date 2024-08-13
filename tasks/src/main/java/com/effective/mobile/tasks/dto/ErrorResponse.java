package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Ответ с кодом и сообщением об ошибке")
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    @Schema(description = "Код ошибки", example = "NOT_FOUND")
    private String code;

    @Schema(description = "Сообщение", example = "Пользователь не найден")
    private String message;
}
