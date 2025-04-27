/**
 * @description Контроллер для работы с сущностью `Task`
 */

package com.chyvacheck.tasktracker.controller;

/**
 * ! lib imports
 */
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * ! java imports
 */
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.service.ITaskService;
import com.chyvacheck.tasktracker.controller.dto.TaskCreateDto;
import com.chyvacheck.tasktracker.controller.dto.TaskIdPathDto;
import com.chyvacheck.tasktracker.core.exceptions.custom.NotFoundTaskException;
import com.chyvacheck.tasktracker.middleware.validate.ValidateMiddleware;
import com.chyvacheck.tasktracker.model.Task;

public class TaskController {
	private final ITaskService taskService;

	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}

	public void registerRoutes(Javalin app) {
		app.get("/tasks", this::getAllTasks);
		app.get("/tasks/{id}", this::getOneTaskById);
		app.post("/tasks", this::createOneTask);
		app.patch("/tasks/{id}", this::completeOneTaskById);
	}

	/**
	 * * Get
	 */

	private void getAllTasks(Context ctx) {
		List<Task> tasks = taskService.getAllTasks();
		ctx.json(tasks);
	}

	private void getOneTaskById(Context ctx) throws Exception {

		TaskIdPathDto dto = ValidateMiddleware.fromPath(ctx, TaskIdPathDto.class);

		Optional<Task> updated = taskService.getOneTaskById(dto.getId());

		if (updated.isPresent()) {
			ctx.json(updated.get());
		} else {
			throw new NotFoundTaskException("Task with this id not found", Map.of("id", dto.getId()));
		}
	}

	/**
	 * * Create
	 */

	private void createOneTask(Context ctx) throws Exception {

		TaskCreateDto dto = ValidateMiddleware.fromBody(ctx, TaskCreateDto.class);

		if (dto == null)
			return;

		Task task = taskService.createOneTask(dto.getTitle(), dto.getDeadline());
		ctx.status(201).json(task);

	}

	/**
	 * * Update
	 */

	private void completeOneTaskById(Context ctx) {
		long id = Long.parseLong(ctx.pathParam("id"));

		Optional<Task> updated = taskService.completeOneTaskById(id);

		if (updated.isPresent()) {
			ctx.json(updated.get());
		} else {
			ctx.status(404).result("Task not found");
		}
	}
}
