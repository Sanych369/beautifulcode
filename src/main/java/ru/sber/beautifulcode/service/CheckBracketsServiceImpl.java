package ru.sber.beautifulcode.service;

import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.sber.beautifulcode.constants.Symbol;
import ru.sber.beautifulcode.dto.response.CheckBracketsResponse;
import ru.sber.beautifulcode.util.MDCUtils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс реализующий логику проверки скобок
 *
 * @author AKostyrev
 */
@Slf4j
@Service
public class CheckBracketsServiceImpl implements CheckBracketsService {

    /**
     * @param text строка из запроса
     * @return возвращает результат проверки валидности скобок в тексте {@link ru.sber.beautifulcode.dto.response.CheckBracketsResponse}
     */
    @Override
    public CheckBracketsResponse checkBrackets(String text) {
        log.info("{} Start processing check brackets with params: [{}]", MDCUtils.getRequestId(), text);

        boolean checkCorrectBrackets = false;
        final CharacterIterator it = new StringCharacterIterator(text);

        boolean isCorrectDelimiters = areDelimitersCorrect(CharacterIterator.DONE, it, it.current());

        if (isCorrectDelimiters) {
            checkCorrectBrackets = checkTextBetweenBracketsPair(text);
        }

        log.info("{} End check brackets method with result: {}", MDCUtils.getRequestId(), checkCorrectBrackets);

        return CheckBracketsResponse.builder()
                .isCorrect(checkCorrectBrackets)
                .build();
    }

    /**
     * Метод проверки текста между скобок
     *
     * @param text строка из запроса
     * @return возвращает соответствие наличия текста между каждой открытой и закрытой скобкой
     */
    @VisibleForTesting
    boolean checkTextBetweenBracketsPair(String text) {
        log.info("{} Start checking text between brackets pair", MDCUtils.getRequestId());
        Pattern pattern = Pattern.compile("\\((.[^\\/S\\(\\)]{1,}?)\\)");
        Matcher matcher = pattern.matcher(text);

        boolean hasTextBetweenBrackets = false;

        while (matcher.find()) {
            if (StringUtils.hasText(matcher.group(1))) {
                hasTextBetweenBrackets = true;
            } else {
                log.info("{} Found non matches result for {}", MDCUtils.getRequestId(), text);
                return false;
            }
        }

        return hasTextBetweenBrackets;
    }

    /**
     * Метод проверки соответствия открытости и закрытости скобок
     * При необходимости добавления дополнительных символов - можно переделать данный метод путём использования HashMap
     *
     * @param closingDelimiter закрывающий элемент
     * @param it               итератор символов по строке
     * @param currentCharacter текущий элемент
     * @return возвращает соответствие открывающих и закрывающих символов
     */
    @VisibleForTesting
    boolean areDelimitersCorrect(Character closingDelimiter, CharacterIterator it, Character currentCharacter) {
        log.info("{} Start delimiters correct method", MDCUtils.getRequestId());
        boolean correctDelimitersInside = true;

        while (currentCharacter != CharacterIterator.DONE && correctDelimitersInside) {

            if (currentCharacter.equals(closingDelimiter)) {
                return true;
            }

            if (Symbol.CLOSE_BRACKET.getCh() == currentCharacter) {
                return false;
            }

            if (Symbol.OPEN_BRACKET.getCh() == currentCharacter) {
                correctDelimitersInside = areDelimitersCorrect(Symbol.CLOSE_BRACKET.getCh(), it, it.next());
            }
            currentCharacter = it.next();
        }

        return correctDelimitersInside && currentCharacter.equals(closingDelimiter);
    }
}
