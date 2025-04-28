
/**
 * @file ServiceProcessType.java
 * 
 * @description
 * Перечисление возможных типов процессов, выполняемых сервисами.
 * Используется для описания результата обработки запроса в слое сервиса.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.response.service;

/**
 * Перечисление типов процессов выполнения сервисов.
 */
public enum ServiceProcessType {

	FOUND, // Ресурс найден
	CREATED, // Ресурс успешно создан
	UPDATED, // Ресурс успешно обновлён
	DELETED, // Ресурс успешно удалён
	NOTHING // Ничего не изменено (например, задача уже выполнена)
}