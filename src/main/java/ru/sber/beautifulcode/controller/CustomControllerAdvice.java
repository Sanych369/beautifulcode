package ru.sber.beautifulcode.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.sber.beautifulcode.dto.ErrorResponse;
import ru.sber.beautifulcode.exception.CheckBracketsException;

/**
 * Перехватчик исключений
 * Отдаём по формату DTO {@link ru.sber.beautifulcode.dto.ErrorResponse}
 */
@Slf4j
@ControllerAdvice
public class CustomControllerAdvice {

    /**
     * Перехватываем кастомное исключение
     *
     * @param exception кастомное исключение {@link ru.sber.beautifulcode.exception.CheckBracketsException}
     * @return возвращает HTTP ответ по формату
     */
    @ExceptionHandler(CheckBracketsException.class)
    public ResponseEntity<ErrorResponse> handleCheckBracketsException(CheckBracketsException exception) {
        log.error("CheckBracketsException. Cause: {}", ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .details(exception.getDetails())
                        .message(exception.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Перехватываем остальные исключения не изменяя http status response
     *
     * @param exception исключение {@link java.lang.Exception}
     * @return возвращает HTTP ответ по формату
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Unexpected error {}. Cause: {}", ExceptionUtils.getMessage(exception), ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(exception.getMessage())
                        .details(String.valueOf(exception.getCause()))
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
