
/**
 * @file EnvironmentUtil.java
 * 
 * @description
 * Утилитный класс для определения текущего окружения выполнения приложения.
 * Предоставляет методы для проверки активного режима: production, development или debug.
 * 
 * @usage
 * Используется для изменения поведения приложения в зависимости от среды исполнения.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.utils;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.base.BaseUtil;

/**
 * Утилита для управления и определения текущего окружения приложения.
 */
public class EnvironmentUtil extends BaseUtil {

	private static final String environment = "development";
	private static final boolean isProduction;
	private static final boolean isDevelopment;
	private static final boolean isDebug;

	static {
		isProduction = "production".equalsIgnoreCase(environment);
		isDevelopment = "development".equalsIgnoreCase(environment);
		isDebug = "debug".equalsIgnoreCase(environment);
	}

	/**
	 * Проверяет, является ли текущее окружение production.
	 *
	 * @return true, если production
	 */
	public static boolean isProduction() {
		return isProduction;
	}

	/**
	 * Проверяет, НЕ является ли текущее окружение production.
	 *
	 * @return true, если НЕ production
	 */
	public static boolean isNotProduction() {
		return !isProduction;
	}

	/**
	 * Проверяет, является ли текущее окружение development.
	 *
	 * @return true, если development
	 */
	public static boolean isDevelopment() {
		return isDevelopment;
	}

	/**
	 * Проверяет, НЕ является ли текущее окружение development.
	 *
	 * @return true, если НЕ development
	 */
	public static boolean isNotDevelopment() {
		return !isDevelopment();
	}

	/**
	 * Проверяет, является ли текущее окружение debug.
	 *
	 * @return true, если debug
	 */
	public static boolean isDebug() {
		return isDebug;
	}

	/**
	 * Проверяет, НЕ является ли текущее окружение debug.
	 *
	 * @return true, если НЕ debug
	 */
	public static boolean isNotDebug() {
		return !isDebug();
	}

	/**
	 * Получить текущее окружение как строку.
	 *
	 * @return строковое представление текущего окружения
	 */
	public static String getEnvironment() {
		return environment;
	}
}
