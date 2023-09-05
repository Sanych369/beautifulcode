package ru.sber.beautifulcode.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.beautifulcode.dto.ErrorResponse;
import ru.sber.beautifulcode.dto.request.CheckBracketsRequest;
import ru.sber.beautifulcode.dto.response.CheckBracketsResponse;
import ru.sber.beautifulcode.exception.CheckBracketsException;
import ru.sber.beautifulcode.service.CheckBracketsService;
import ru.sber.beautifulcode.validate.CheckBracketsValidator;

/**
 * Контроллер обработки запросов
 */
@Tag(name = "Контроллер для проверки скобок")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checkBrackets")
public class BeautifulCodeController {

    private final CheckBracketsValidator validator;
    private final CheckBracketsService checkBracketsService;

    /**
     * @param request входящий HTTP запрос
     * @return ответ {@link ru.sber.beautifulcode.dto.response.CheckBracketsResponse}
     * @throws CheckBracketsException в случае невалидности данных запроса
     */
    @Operation(
            summary = "Проверка текста",
            description = "Позволяет проверить текст на корректность скобок"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CheckBracketsResponse.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public CheckBracketsResponse checkBrackets(
            @Parameter(schema = @Schema(implementation = CheckBracketsRequest.class))
            @RequestBody CheckBracketsRequest request) throws CheckBracketsException {
        validator.validateRequest(request);
        return checkBracketsService.checkBrackets(request.getText());
    }
}
