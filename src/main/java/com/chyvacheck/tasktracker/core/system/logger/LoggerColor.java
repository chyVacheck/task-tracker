
/**
 * @file LoggerColor.java
 * 
 * @description
 * Перечисление ANSI-цветов для использования в логах в консоли.
 * Позволяет задавать цвета для разных частей сообщения.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.system.logger;

/**
 * ANSI-цвета для раскраски сообщений логгирования в терминале.
 */
public enum LoggerColor {
	RESET("\u001B[0m"), // Сброс цвета
	BLACK("\u001B[30m"), // Черный
	RED("\u001B[31m"), // Красный
	GREEN("\u001B[32m"), // Зеленый
	YELLOW("\u001B[33m"), // Желтый
	CYAN("\u001B[36m"), // Голубой
	BLUE("\u001B[34m"), // Синий
	PURPLE("\u001B[35m"), // Фиолетовый
	WHITE("\u001B[37m"), // Белый
	GRAY("\u001B[90m"); // Серый (тёмный)

	private final String code;

	LoggerColor(String code) {
		this.code = code;
	}

	// ? Getters

	/**
	 * Получить ANSI-код цвета для консоли.
	 *
	 * @return строка ANSI-кода цвета
	 */
	public String getCode() {
		return code;
	}

}