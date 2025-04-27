package com.chyvacheck.tasktracker.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class TaskIdPathDto {
	@NotNull
	@PositiveOrZero
	private Long id;

	public Long getId() {
		return id;
	}
}