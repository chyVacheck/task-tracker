
/**
 * @file BaseMiddleware.java
 * 
 * @description
 * Абстрактный базовый класс для всех Middleware-компонентов приложения.
 * Обеспечивает стандартные характеристики для промежуточных обработчиков, таких как валидация запросов, аутентификация, логирование и другие системные операции.
 * 
 * @details
 * Основные задачи BaseMiddleware:
 * - Хранение типа модуля ({@link ModuleType#MIDDLEWARE}) для целей логирования и структурирования проекта
 * - Централизация архитектурных решений для всех middleware-слоёв
 * - Стандартизация работы промежуточных компонентов приложения
 * 
 * Использование:
 * Все промежуточные обработчики должны наследовать этот базовый класс напрямую или через специализированные расширения.
 * 
 * Пример наследования:
 * <pre>
 * public class ValidateMiddleware extends BaseMiddleware
 * </pre>
 * 
 * @example
 * <pre>
 * BaseMiddleware middleware = new ValidateMiddleware();
 * middleware.getModuleName(); // "com.chyvacheck.tasktracker.middleware.ValidateMiddleware"
 * middleware.getModuleType(); // MIDDLEWARE
 * </pre>
 * 
 * @see ModuleType
 * @see BaseModule
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.base;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.system.ModuleType;

/**
 * Абстрактный базовый класс для всех Middleware-компонентов приложения.
 * <p>
 * Используется для обеспечения единообразия и централизованного управления
 * характеристиками промежуточных обработчиков.
 */
public abstract class BaseMiddleware extends BaseModule {

	/**
	 * Конструктор базового Middleware.
	 * Устанавливает тип модуля как {@link ModuleType#MIDDLEWARE} и сохраняет ссылку
	 * на класс.
	 *
	 * @param moduleClass класс, представляющий модуль
	 */
	public BaseMiddleware(Class<?> moduleClass) {
		super(ModuleType.MIDDLEWARE, moduleClass);
	}
}
