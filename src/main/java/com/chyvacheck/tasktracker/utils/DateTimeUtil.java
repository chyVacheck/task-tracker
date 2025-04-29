
/**
 * @file DateTimeUtil.java
 * 
 * @description
 * Утилитный класс для работы с датой и временем в формате логирования.
 * Предоставляет методы для получения текущего времени или указанного времени
 * в стандартизированном формате для логов.
 * 
 * @format
 * Формат времени: {@code yyyy-MM-dd HH:mm:ss.SSSSSS} (UTC)
 * 
 * @usage
 * Используется для добавления временных меток к лог-сообщениям.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.utils;

/**
 * ! java imports
 */
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.base.BaseUtil;

/**
 * Утилита для получения отформатированных временных меток для логирования.
 */
public class DateTimeUtil extends BaseUtil {
	private static final DateTimeFormatter LOG_DATE_FORMATTER = DateTimeFormatter
			.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
			.withZone(ZoneId.of("UTC"));

	/**
	 * Получить текущую временную метку в формате для логов.
	 *
	 * @return строка с текущей датой и временем в UTC
	 */
	public static String getLogTimestamp() {
		return LOG_DATE_FORMATTER.format(Instant.now());
	}

	/**
	 * Получить временную метку для заданного времени в формате для логов.
	 *
	 * @param time объект Instant для форматирования
	 * @return строка с датой и временем в UTC
	 */
	public static String getLogTimestamp(Instant time) {
		return LOG_DATE_FORMATTER.format(time);
	}
}
