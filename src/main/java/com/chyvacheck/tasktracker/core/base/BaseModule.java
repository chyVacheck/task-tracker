
/**
 * @file BaseModule.java
 * 
 * @description
 * Абстрактный базовый класс для всех модулей приложения.
 * Определяет общие характеристики модуля, такие как имя класса и его тип (контроллер, сервис, репозиторий и т.д.).
 * 
 * @details
 * Основные задачи BaseModule:
 * - Хранение типа модуля (ModuleType) для использования в логировании, мониторинге и структурировании проекта
 * 
 * Использование:
 * Все базовые компоненты приложения (контроллеры, сервисы, репозитории, обработчики ошибок) должны наследовать этот класс.
 * 
 * Пример наследования:
 * public class TaskController extends BaseController extends BaseModule
 * 
 * @example
 * BaseModule module = new TaskController();
 * module.getModuleName(); // \"com.chyvacheck.tasktracker.controller.TaskController\"
 * module.getModuleType(); // CONTROLLER
 * 
 * @see ModuleType
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
import com.chyvacheck.tasktracker.core.system.logger.Logger;

/**
 * Абстрактный базовый класс для всех модулей приложения.
 */
public abstract class BaseModule {

	protected final ModuleType moduleType;
	private final Class<?> moduleClass;
	private final Logger logger = Logger.getInstance();

	/**
	 * Конструктор базового модуля.
	 * Автоматически определяет имя модуля и сохраняет тип модуля.
	 *
	 * @param moduleType тип модуля (например, CONTROLLER, SERVICE)
	 */
	protected BaseModule(ModuleType moduleType, Class<?> moduleClass) {
		this.moduleType = moduleType;
		this.moduleClass = moduleClass;

	}

	/**
	 * Получить полное имя модуля (имя класса с пакетом).
	 *
	 * @return имя модуля
	 */
	public String getModuleName() {
		return moduleClass.getName();
	}

	/**
	 * Получить тип модуля.
	 *
	 * @return тип модуля (ModuleType)
	 */
	public ModuleType getModuleType() {
		return moduleType;
	}

	/**
	 * * logger
	 */

	protected void debug(String message, Map<String, Object> details) {
		this.logger.debug(message, this.moduleType, getModuleName(), details);
	}

	protected void info(String message, Map<String, Object> details) {
		this.logger.info(message, this.moduleType, getModuleName(), details);
	}

	protected void warn(String message, Map<String, Object> details) {
		this.logger.warn(message, this.moduleType, getModuleName(), details);
	}

	protected void error(String message, Map<String, Object> details, Throwable throwable) {
		this.logger.error(message, this.moduleType, getModuleName(), details, throwable);
	}
}
