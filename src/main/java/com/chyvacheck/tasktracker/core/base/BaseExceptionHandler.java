
/**
 * @file BaseExceptionHandler.java
 * 
 * @extends BaseModule
 * 
 * @description
 * Абстрактный базовый класс для всех обработчиков исключений в системе.
 * Наследуется от BaseModule и устанавливает тип модуля как SYSTEM.
 * 
 * @details
 * Используется для создания централизованных обработчиков ошибок:
 * - Глобальный перехват исключений (например, ApiException, RuntimeException)
 * - Обработка пользовательских и системных ошибок
 * - Формирование стандартизированных ответов об ошибках для клиентов API
 * 
 * Все специфичные обработчики ошибок должны наследоваться от BaseExceptionHandler
 * для соблюдения единого архитектурного стиля.
 * 
 * Пример наследования:
 * public class GlobalExceptionHandler extends BaseExceptionHandler
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
 * Абстрактный базовый класс для всех обработчиков исключений.
 */
public abstract class BaseExceptionHandler extends BaseModule {

	/**
	 * Конструктор базового обработчика исключений.
	 * Устанавливает тип модуля как SYSTEM.
	 */
	public BaseExceptionHandler(Class<?> moduleClass) {
		super(ModuleType.SYSTEM, moduleClass);
	}

}
