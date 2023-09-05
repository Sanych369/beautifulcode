package ru.sber.beautifulcode.constants;

/**
 * Перечисление открывающих и закрывающих скобок
 */
public enum Symbol {

    OPEN_BRACKET('('),
    CLOSE_BRACKET(')');

    private final char ch;

    Symbol(char ch) {
        this.ch = ch;
    }

    public char getCh() {
        return this.ch;
    }
}
