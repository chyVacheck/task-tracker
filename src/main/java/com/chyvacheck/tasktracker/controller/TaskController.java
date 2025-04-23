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
import java.util.Optional;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.service.ITaskService;
import com.chyvacheck.tasktracker.model.Task;

public class TaskController {
	private final ITaskService taskService;

	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}

	public void registerRoutes(Javalin app) {
		app.get("/tasks", this::getAllTasks);
		app.get("/tasks/{id}", this::completeOneTaskById);
		app.post("/tasks", this::createOneTask);
		app.patch("/tasks/{id}", this::completeOneTaskById);
	}

	private void getAllTasks(Context ctx) {
		List<Task> tasks = taskService.getAllTasks();
		ctx.json(tasks);
	}

	private void createOneTask(Context ctx) {
		String title = ctx.body(); // допустим, просто строка
		Task task = taskService.createOneTask(title);
		ctx.status(201).json(task);
	}

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
