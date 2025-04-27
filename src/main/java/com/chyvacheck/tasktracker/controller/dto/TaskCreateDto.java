
/**
 * @file TaskCreateDto.java
 * 
 * @description
 * Data Transfer Object (DTO) для создания новой задачи через API TaskController.
 * Используется для валидации данных, отправляемых в теле POST-запроса на создание задачи.
 * 
 * @details
 * DTO включает следующие поля:
 * - title: Название задачи (обязательное, от 3 до 32 символов)
 * - deadline: Дедлайн выполнения задачи (обязательный или текущий момент, не в прошлом)
 * 
 * Пример использования:
 * POST /tasks
 * {
 *   "title": "Fix bug in production",
 *   "deadline": "2025-05-01T12:00:00"
 * }
 * 
 * При нарушении условий валидации сервер вернёт ошибку 400 с описанием полей.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.controller.dto;

/**
 * ! lib imports
 */
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ! java imports
 */
import java.time.LocalDateTime;

/**
 * DTO для валидации тела POST-запроса на создание задачи.
 */
public class TaskCreateDto {

	/**
	 * Название задачи.
	 * 
	 * Требования:
	 * - Не должно быть пустым
	 * - Минимум 3 символа
	 * - Максимум 32 символа
	 */
	@NotBlank(message = "Title must not be empty")
	@Size(min = 3, max = 32, message = "Title must be between 3 and 32 characters")
	private String title;

	/**
	 * Дедлайн выполнения задачи.
	 * 
	 * Требования:
	 * - Должен быть текущим или будущим временем
	 */
	@FutureOrPresent(message = "Deadline must be in present or in future")
	private LocalDateTime deadline;

	public TaskCreateDto() {
		// Default constructor for Jackson
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

}
