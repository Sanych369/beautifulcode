package ru.sber.beautifulcode.service;

import ru.sber.beautifulcode.dto.response.CheckBracketsResponse;

/**
 * Интерфейс сервиса
 */
public interface CheckBracketsService {

    CheckBracketsResponse checkBrackets(String text);
}
