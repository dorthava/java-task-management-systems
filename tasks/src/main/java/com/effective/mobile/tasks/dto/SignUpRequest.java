package com.effective.mobile.tasks.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "Запрос на регистрацию")
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Schema(description = "Имя пользователя", example = "John")
    @Size(min = 1, max = 50, message = "Имя пользователя должно содержать от 1 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String username;

    @Schema(description = "Адрес электронной почты", example = "johndoe@gmail.com")
    @Size(min = 1, max = 255, message = "Адрес электронной почты должен содержать от 1 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 1, max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;
}