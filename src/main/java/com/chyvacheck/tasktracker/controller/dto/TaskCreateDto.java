/**
 * @description DTO для создания задачи
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

public class TaskCreateDto {

	@NotBlank(message = "Title can not be empty")
	@Size(min = 3, max = 32, message = "Title must be between 3 and 32 characters")
	private String title;

	@FutureOrPresent(message = "Deadline must be in present or in future")
	private LocalDateTime deadline;

	public TaskCreateDto() {
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

}
