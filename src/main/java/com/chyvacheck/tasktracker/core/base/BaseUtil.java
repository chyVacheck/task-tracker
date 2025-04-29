
/**
 * @file BaseUtil.java
 * @description
 * Абстрактный базовый класс для всех утилитарных (static-only) классов.
 * Предотвращает создание экземпляров утилитарных классов путём выбрасывания
 * {@link UnsupportedOperationException} в конструкторе.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.base;

/**
 * Абстрактный базовый класс для утилитарных (static-only) классов.
 * <p>
 * Предотвращает создание экземпляров утилитарных классов путём выбрасывания
 * {@link UnsupportedOperationException} в конструкторе.
 * <p>
 * Все классы, содержащие только статические методы и не предназначенные для
 * создания объектов,
 * могут наследоваться от этого класса для обеспечения единообразия и защиты от
 * неправильного использования.
 *
 * @author Dmytro Shakh
 * @see java.lang.UnsupportedOperationException
 */
public abstract class BaseUtil {

	// Private constructor to prevent instantiation
	protected BaseUtil() {
		throw new UnsupportedOperationException(
				getClass().getSimpleName() + " is a utility class and cannot be instantiated.");
	}

}
