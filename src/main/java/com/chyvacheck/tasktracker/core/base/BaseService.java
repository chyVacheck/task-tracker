
/**
 * @file BaseService.java
 * 
 * @extends BaseModule
 * 
 * @description
 * Абстрактный базовый класс для всех сервисов бизнес-логики приложения.
 * Наследуется от BaseModule и устанавливает тип модуля как SERVICE.
 * 
 * @details
 * Сервисы инкапсулируют бизнес-логику приложения и взаимодействуют с репозиториями для обработки данных.
 * 
 * Основные задачи сервисов:
 * - Реализация бизнес-правил
 * - Взаимодействие с репозиториями
 * - Обработка данных перед возвратом в контроллеры
 * 
 * Пример наследования:
 * public class TaskService extends BaseService
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
 * Абстрактный базовый класс для всех сервисов приложения.
 */
public abstract class BaseService extends BaseModule {

	/**
	 * Конструктор базового сервиса.
	 * Устанавливает тип модуля как SERVICE.
	 */
	protected BaseService(Class<?> moduleClass) {
		super(ModuleType.SERVICE, moduleClass);
	}

}