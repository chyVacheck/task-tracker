
/**
 * @file ModuleType.java
 * @author Dmytro Shakh
 */
package com.chyvacheck.tasktracker.core.system;

/**
 * Перечисление типов классов
 */
public enum ModuleType {
	CONTROLLER, // Контроллеры
	SERVICE, // Сервисы
	REPOSITORY, // Репозитории (работа с БД)
	API, // Внешние API-запросы
	MAPPER, // Мапперы (DTO -> Entity и обратно)
	CACHE, // Кеш
	MIDDLEWARE, // Middleware (обработка запроса)
	DATABASE, // DataBase (работа с БД)
	SYSTEM, // Системные логи
	UNKNOWN; // Неизвестный тип
}
