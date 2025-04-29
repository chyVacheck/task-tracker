
/**
 * @file LoggerConfig.java
 * 
 * @description
 * Конфигурация для настройки стилей логгирования:
 * - Ширина колонок
 * - Цвет уровней логов
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.system.logger;

/**
 * Конфигурационные параметры логгера.
 */
public final class LoggerConfig {

	private LoggerConfig() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	// Ширина полей
	public static final int LEVEL_WIDTH = 5; // Фиксированная ширина уровня (DEBUG, INFO)
	public static final int TIME_WIDTH = 24; // Фиксированная ширина времени
	public static final int DURATION_WIDTH = 3; // Фиксированная ширина времени
	public static final int TYPE_WIDTH = 12; // Фиксированная ширина типа класса
	public static final int CLASS_WIDTH = 26; // Фиксированная ширина названия класса

	// Цвета уровней логов
	public static final LoggerColor DEBUG_COLOR = LoggerColor.PURPLE; // Фиксированный цвет
	public static final LoggerColor INFO_COLOR = LoggerColor.GREEN; // Фиксированный цвет
	public static final LoggerColor WARN_COLOR = LoggerColor.YELLOW; // Фиксированный цвет
	public static final LoggerColor ERROR_COLOR = LoggerColor.RED; // Фиксированный цвет

}