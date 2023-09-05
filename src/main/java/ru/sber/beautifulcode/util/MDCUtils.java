package ru.sber.beautifulcode.util;

import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

/**
 * Класс трассировки requestId header'а
 */
@UtilityClass
public class MDCUtils {
    /**
     * Наименование параметра для трассировки
     */
    private final String REQUEST_ID = "requestId";

    /**
     * Получение значения requestId из контекста
     * @return requestId
     */
    public String getRequestId() {
        return MDC.get(REQUEST_ID);
    }

    /**
     * Сохранение в контекст значения requestId
     * @param requestId значение полученное либо из header'а запроса, либо сгенерированное
     * {@link ru.sber.beautifulcode.configuration.HandleInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, Object)}
     */
    public void putRequestId(String requestId) {
        MDC.put(REQUEST_ID, requestId);
    }

    /**
     * Очистка текущего requestId из контекста
     */
    public void clearRequestId() {
        MDC.remove(REQUEST_ID);
    }
}
