
/**
 * @file TaskIdPathDto.java
 * 
 * @description
 * Data Transfer Object (DTO) предназначенный для получения 
 * параметра `id` из URL-пути в эндпоинтах, связанных с сущностью Task.
 * Используется для валидации ID задачи, передаваемого через path-параметры.
 * 
 * @details
 * Содержит только одно поле `id`, которое должно быть:
 * - Не null (@NotNull)
 * - Положительным числом или нулём (@PositiveOrZero)
 * 
 * Пример использования:
 * GET /tasks/{id}
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.controller.dto;

/**
 * ! lib imports
 */
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * DTO для валидации path-параметра `id` в запросах, связанных с задачами.
 */
public class TaskIdPathDto {

	/**
	 * Идентификатор задачи.
	 */
	@NotNull(message = "Task id must not be null")
	@PositiveOrZero(message = "Task id must be zero or positive")
	private Long id;

	public Long getId() {
		return id;
	}
}