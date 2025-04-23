/**
 * @description Точка входа приложения
 */

package com.chyvacheck.tasktracker;

/**
 * ! lib imports
 */
import io.javalin.Javalin;

import com.chyvacheck.tasktracker.controller.TaskController;
/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.repository.ITaskRepository;
import com.chyvacheck.tasktracker.repository.impl.InMemoryTaskRepository;
import com.chyvacheck.tasktracker.service.ITaskService;
import com.chyvacheck.tasktracker.service.TaskService;

public class Main {
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(7070);

		ITaskRepository taskRepository = new InMemoryTaskRepository();

		ITaskService taskService = new TaskService(taskRepository);

		TaskController taskController = new TaskController(taskService);

		taskController.registerRoutes(app);

		app.get("/", ctx -> {
			ctx.contentType("text/plain; charset=UTF-8");
			ctx.result("Добро пожаловать в Task Tracker! 🚀");
		});
	}
}