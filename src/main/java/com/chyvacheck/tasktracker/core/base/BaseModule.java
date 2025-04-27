
/**
 * @file BaseModule.java
 * 
 * @description
 * Абстрактный базовый класс для всех модулей приложения.
 * Определяет общие характеристики модуля, такие как имя класса и его тип (контроллер, сервис, репозиторий и т.д.).
 * 
 * @details
 * Основные задачи BaseModule:
 * - Автоматическое определение имени модуля через StackWalker
 * - Хранение типа модуля (ModuleType) для использования в логировании, мониторинге и структурировании проекта
 * 
 * Использование:
 * Все базовые компоненты приложения (контроллеры, сервисы, репозитории, обработчики ошибок) должны наследовать этот класс.
 * 
 * Пример наследования:
 * public class TaskController extends BaseController extends BaseModule
 * 
 * Примечание:
 * Использование StackWalker минимально нагружает производительность и позволяет надёжно определять реальный класс потомка.
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
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.system.ModuleType;

/**
 * Абстрактный базовый класс для всех модулей приложения.
 */
public abstract class BaseModule {

	private final String moduleName;
	protected final ModuleType moduleType;

	/**
	 * Конструктор базового модуля.
	 * Автоматически определяет имя модуля и сохраняет тип модуля.
	 *
	 * @param moduleType тип модуля (например, CONTROLLER, SERVICE)
	 */
	protected BaseModule(ModuleType moduleType) {
		this.moduleName = resolveModuleName();
		this.moduleType = moduleType;
	}

	/**
	 * Определяет имя модуля через StackWalker.
	 * 
	 * @return полное имя класса потомка
	 */
	private static String resolveModuleName() {
		return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
				.getCallerClass()
				.getName();
	}

	/**
	 * Получить полное имя модуля (имя класса с пакетом).
	 *
	 * @return имя модуля
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Получить тип модуля.
	 *
	 * @return тип модуля (ModuleType)
	 */
	public ModuleType getModuleType() {
		return moduleType;
	}

}
