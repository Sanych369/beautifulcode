package ru.sber.beautifulcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO ответа при исключетельных ситуациях
 */
@Setter
@Getter
@Builder
@Schema(description = "Ответ при ошибках")
public class ErrorResponse {

    @Schema(description = "Сообщение об ошибке")
    private String message;

    @Schema(description = "Детали ошибки")
    private String details;
}
