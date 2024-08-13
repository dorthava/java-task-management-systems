package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на аутентификацию")
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    @Schema(description = "email пользователя", example = "Albert@gmail.com")
    @Size(min = 1, max = 255, message = "email должен содержать от 1 до 255 символов")
    @NotBlank(message = "email не может быть пустым")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 1, max = 255, message = "Длина пароля должна быть не более 255 символов")
    @NotBlank(message = "Пароль не может быть пустыми")
    private String password;
}
