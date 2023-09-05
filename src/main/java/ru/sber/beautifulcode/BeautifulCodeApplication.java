package ru.sber.beautifulcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка входа в приложение
 */
@SpringBootApplication
public class BeautifulCodeApplication {
    /**
     * Метод для старта spring-boot приложения
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(BeautifulCodeApplication.class, args);
    }
}
