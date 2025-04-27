
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
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.system.ModuleType;

/**
 * Абстрактный базовый класс для всех контроллеров приложения.
 */
public abstract class BaseController extends BaseModule {

	/**
	 * Конструктор базового контроллера.
	 * Устанавливает тип модуля как CONTROLLER.
	 */
	protected BaseController() {
		super(ModuleType.CONTROLLER);
	}
}