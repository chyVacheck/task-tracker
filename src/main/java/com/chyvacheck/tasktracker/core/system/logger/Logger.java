
/**
 * @file Logger.java
 * @author Dmytro Shakh
 * 
 * @description
 * Основной класс логгера для проекта.
 * Поддерживает уровни логирования (DEBUG, INFO, WARN, ERROR),
 * а также цветное форматирование и вывод дополнительных деталей.
 * 
 * Использует:
 * - {@link LoggerLevel}
 * - {@link LoggerColor}
 * - {@link LoggerConfig}
 * - {@link ModuleType}
 * 
 * @usage
 * Logger.getInstance().info("message", moduleType, className, details);
 */

package com.chyvacheck.tasktracker.core.system.logger;

/**
 * ! java imports
 */
import java.util.Map;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.system.ModuleType;
import com.chyvacheck.tasktracker.utils.DateTimeUtil;

/**
 * Основной логгер для проекта.
 */
public class Logger {

	private static Logger INSTANCE = new Logger();

	private Logger() {
	}

	/**
	 * Получить единственный экземпляр логгера (Singleton).
	 *
	 * @return экземпляр Logger
	 */
	public static Logger getInstance() {
		return INSTANCE;
	}

	// Константы ширины столбцов и цветов
	private static final int LevelW = LoggerConfig.LEVEL_WIDTH; // Фиксированная ширина уровня (DEBUG, INFO)
	private static final int TimeW = LoggerConfig.TIME_WIDTH; // Фиксированная ширина времени
	private static final int TypeW = LoggerConfig.TYPE_WIDTH; // Фиксированная ширина типа модуля
	private static final int ClassW = LoggerConfig.CLASS_WIDTH; // Фиксированная ширина названия класса
	private static final int DurationW = LoggerConfig.DURATION_WIDTH; // Фиксированная ширина задержи запуска

	private static final String AC = LoggerColor.CYAN.getCode(); // action color
	private static final String DC = LoggerColor.GRAY.getCode(); // default color
	private static final String TC = LoggerColor.GREEN.getCode(); // type module color
	private static final String RC = LoggerLevel.resetColor(); // reset color

	/**
	 * Логирует сообщение уровня DEBUG.
	 */
	public void debug(String message, ModuleType moduleType, String className, Map<String, Object> details) {
		log(LoggerLevel.DEBUG, message, moduleType, className, details, null);

	}

	/**
	 * Логирует сообщение уровня INFO.
	 */
	public void info(String message, ModuleType moduleType, String className, Map<String, Object> details) {
		log(LoggerLevel.INFO, message, moduleType, className, details, null);

	}

	/**
	 * Логирует сообщение уровня WARN.
	 */
	public void warn(String message, ModuleType moduleType, String className, Map<String, Object> details) {
		log(LoggerLevel.WARN, message, moduleType, className, details, null);

	}

	/**
	 * Логирует сообщение уровня ERROR с возможным исключением.
	 */
	public void error(String message, ModuleType moduleType, String className, Map<String, Object> details,
			Throwable throwable) {
		log(LoggerLevel.ERROR, message, moduleType, className, details, throwable);
	}

	/**
	 * Форматирует текст в квадратных скобках с цветом.
	 */
	private String formatWithBrackets(String message, String color) {
		return String.format("%s[%s%s%s] ", RC, color, message, RC);
	}

	/**
	 * Печатает ключ-значение в консоль.
	 */
	private void printKeyValue(String key, String value) {
		System.out.println(String.format("%s%s: %s%s%s", RC, key, DC, value, RC));
	}

	/**
	 * Основной метод логирования с форматированием и обработкой ошибок.
	 */
	private void log(LoggerLevel level, String message, ModuleType moduleType, String className,
			Map<String, Object> details,
			Throwable throwable) {
		String color = level.getColor();

		// Форматируем сообщения с фиксированной шириной
		String levelFormatted = formatWithBrackets(String.format("%-" + LevelW + "s", level.getName()), color);

		String timeFormatted = DC + String.format("%-" + TimeW + "s ", DateTimeUtil.getLogTimestamp());
		String typeFormatted = TC + String.format("%-" + TypeW + "s", moduleType.name());
		String classFormatted = AC + String.format("%-" + ClassW + "s ", className);

		String durationFormatted;

		// if (RequestIdContext.getRequestId() != null) {
		// long durationMillis = System.currentTimeMillis() -
		// RequestIdContext.getRequestTime();
		// durationFormatted = formatWithBrackets(String.format("+%0" + DurationW +
		// "dms", durationMillis),
		// color);
		// } else {
		// }
		durationFormatted = String.format("%" + (DurationW + 6) + "s", "");

		System.out.printf("%s%s%s%s %s%s%s%n",
				levelFormatted, timeFormatted, durationFormatted, typeFormatted, classFormatted,
				color,
				message);

		// if (RequestIdContext.getRequestId() != null) {
		// printKeyValue("RequestId", RequestIdContext.getRequestId());
		// }

		// Выводим JSON-детали, если есть
		if (details != null && !details.isEmpty()) {
			printKeyValue("Details", details.toString());
		}

		// Логируем stacktrace, если есть ошибка
		if (throwable != null) {
			printKeyValue("Throwable", throwable.toString());
		}
	}

}
