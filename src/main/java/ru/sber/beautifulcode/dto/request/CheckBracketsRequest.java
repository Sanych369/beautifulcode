package ru.sber.beautifulcode.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

/**
 * DTO запроса
 */
@Getter
@Setter
@Validated
@Schema(description = "Входящий запрос")
public class CheckBracketsRequest {

    @NonNull
    @Schema(description = "Текст для проверки")
    private String text;
}
