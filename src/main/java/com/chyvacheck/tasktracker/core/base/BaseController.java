
/**
 * @file BaseController.java
 * 
 * @extends BaseModule
 * 
 * @description
 * Абстрактный базовый класс для всех контроллеров системы.
 * Является специальным типом модуля, определяемым как CONTROLLER в ModuleType.
 * 
 * @details
 * - Автоматически присваивает тип модуля как CONTROLLER
 * - Используется для унификации архитектуры всех контроллеров
 * - Обеспечивает общую точку расширения (если в будущем потребуется добавить базовые методы для всех контроллеров)
 * 
 * Пример наследования:
 * public class TaskController extends BaseController
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.base;

/**
 * ! java imports
 */
import java.util.Map;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.system.ModuleType;

/**
 * Абстрактный базовый класс для всех контроллеров приложения.
 */
public abstract class BaseController extends BaseModule {

	/**
	 * Базовый путь маршрутов контроллера (например, /tasks).
	 */
	protected final String basePath;

	/**
	 * Конструктор базового контроллера.
	 * Устанавливает тип модуля как CONTROLLER.
	 *
	 * @param basePath    базовый путь для маршрутов контроллера
	 * @param moduleClass класс
	 */
	protected BaseController(Class<?> moduleClass, String basePath) {
		super(ModuleType.CONTROLLER, moduleClass);
		this.basePath = basePath;
	}

	protected long logRequestStart(String methodName, String path, Map<String, ?> params) {
		long startTime = System.currentTimeMillis();
		this.info("Incoming request", Map.of(
				"method", methodName,
				"path", path,
				"params", params));
		return startTime;
	}

	protected void logRequestEnd(String methodName, String path, long startTime) {
		long duration = System.currentTimeMillis() - startTime;
		this.info("Outgoing response", Map.of(
				"method", methodName,
				"path", path,
				"durationMs", duration));
	}
}