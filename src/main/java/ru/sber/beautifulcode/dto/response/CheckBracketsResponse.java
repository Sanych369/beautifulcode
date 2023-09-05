package ru.sber.beautifulcode.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO ответа
 */
@Setter
@Getter
@Builder
@Schema(description = "Ответ API")
public class CheckBracketsResponse {

    @JsonProperty("isCorrect")
    @Schema(description = "Флаг корректности текста")
    private boolean isCorrect;
}
