
/**
 * @file LoggerLevel.java
 * 
 * @description
 * Перечисление уровней логирования с привязанными цветами для консоли.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.system.logger;

/**
 * Уровни логирования, каждый с фиксированным цветом вывода.
 */
public enum LoggerLevel {
	DEBUG(LoggerConfig.DEBUG_COLOR), // Фиолетовый
	INFO(LoggerConfig.INFO_COLOR), // Зеленый
	WARN(LoggerConfig.WARN_COLOR), // Желтый
	ERROR(LoggerConfig.ERROR_COLOR); // Красный

	private final LoggerColor color;

	LoggerLevel(LoggerColor color) {
		this.color = color;
	}

	/**
	 * Получить имя уровня логирования.
	 *
	 * @return строка имени уровня
	 */
	public String getName() {
		return super.name();
	}

	/**
	 * Получить ANSI-код цвета для уровня логирования.
	 *
	 * @return строка ANSI-кода
	 */
	public String getColor() {
		return color.getCode();
	}

	/**
	 * Получить ANSI-сброс цвета.
	 *
	 * @return строка сброса цвета
	 */
	public static String resetColor() {
		return LoggerColor.RESET.getCode();
	}
}
