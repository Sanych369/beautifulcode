package ru.sber.beautifulcode.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sber.beautifulcode.dto.response.CheckBracketsResponse;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CheckBracketsServiceImplTest {

    private final String POSITIVE_STRING = "(as)123(sa)123(fs)";
    private final String NEGATIVE_STING = "(as((123()))))";

    @Autowired
    private CheckBracketsServiceImpl service;

    @Test
    void checkBracketsPositive() {
        CheckBracketsResponse actual = service.checkBrackets(POSITIVE_STRING);
        assertNotNull(actual);
        assertTrue(actual.isCorrect());
    }

    @Test
    void checkBracketsNegative() {
        CheckBracketsResponse actual = service.checkBrackets(NEGATIVE_STING);
        assertNotNull(actual);
        assertFalse(actual.isCorrect());
    }

    @Test
    void checkTextBetweenBracketsPairPositive() {
        boolean actual = service.checkTextBetweenBracketsPair(POSITIVE_STRING);
        assertTrue(actual);
    }

    @Test
    void checkTextBetweenBracketsPairNegative() {
        boolean actual = service.checkTextBetweenBracketsPair(NEGATIVE_STING);
        assertFalse(actual);
    }

    @Test
    void areDelimitersCorrectPositive() {
        final CharacterIterator it = new StringCharacterIterator(POSITIVE_STRING);
        boolean actual = service.areDelimitersCorrect(CharacterIterator.DONE, it, it.current());
        assertTrue(actual);

    }

    @Test
    void areDelimitersCorrectNegative() {
        final CharacterIterator it = new StringCharacterIterator(NEGATIVE_STING);
        boolean actual = service.areDelimitersCorrect(CharacterIterator.DONE, it, it.current());
        assertFalse(actual);
    }
}