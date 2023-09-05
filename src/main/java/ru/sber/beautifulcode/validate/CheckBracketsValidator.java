package ru.sber.beautifulcode.validate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.sber.beautifulcode.dto.request.CheckBracketsRequest;
import ru.sber.beautifulcode.exception.CheckBracketsException;

/**
 * Проверяем наличие в поле text входящего request символов
 */
@Component
public class CheckBracketsValidator {
    /**
     * @param request текущий запрос
     * @throws CheckBracketsException если в теле запроса отсутствует text
     * {@link ru.sber.beautifulcode.dto.request.CheckBracketsRequest#getText()}
     */
    public void validateRequest(CheckBracketsRequest request) throws CheckBracketsException {
        if (!Boolean.TRUE.equals(StringUtils.hasText(request.getText()))) {
            throw new CheckBracketsException("Text is empty or null", "Не найдены символы для проверки");
        }
    }
}
