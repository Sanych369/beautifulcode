package ru.sber.beautifulcode.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Кастомный exception
 */
@Getter
@Setter
public class CheckBracketsException extends Exception {

    private String message;
    private String details;

    public CheckBracketsException(String message, String details) {
        super();
        this.message = message;
        this.details = details;
    }

}
