
/**
 * @file TaskController.java
 * 
 * @description
 * Контроллер для управления задачами (Task) через HTTP API.
 * Реализует CRUD-операции для сущности Task, включая:
 * - Получение списка задач
 * - Получение одной задачи по ID
 * - Создание новой задачи
 * - Завершение задачи
 * 
 * @details
 * Роуты:
 * - GET /tasks — получить все задачи
 * - GET /tasks/{id} — получить задачу по ID
 * - POST /tasks — создать новую задачу
 * - PATCH /tasks/{id} — отметить задачу как выполненную
 * 
 * Использует валидацию данных через ValidateMiddleware.
 * Генерирует стандартные ответы и ошибки с использованием базовых исключений.
 * 
 * @author
 * Dmytro Shakh
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
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.exceptions.custom.NotFoundTaskException;
import com.chyvacheck.tasktracker.middleware.validate.ValidateMiddleware;
import com.chyvacheck.tasktracker.controller.dto.TaskIdPathDto;
import com.chyvacheck.tasktracker.controller.dto.TaskCreateDto;
import com.chyvacheck.tasktracker.service.ITaskService;
import com.chyvacheck.tasktracker.model.Task;

/**
 * Контроллер для работы с задачами (Task).
 */

	private final ITaskService taskService;

	/**
	 * Конструктор TaskController.
	 * 
	 * @param taskService сервис для работы с задачами
	 */
	public TaskController(ITaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * Регистрирует все маршруты (роуты) для задач в Javalin приложении.
	 * 
	 * @param app экземпляр Javalin
	 */
	public void registerRoutes(Javalin app) {
		app.get("/tasks", this::getAllTasks);
		app.get("/tasks/{id}", this::getOneTaskById);
		app.post("/tasks", this::createOneTask);
		app.patch("/tasks/{id}", this::completeOneTaskById);
	}

	/**
	 * * Get
	 */

	/**
	 * Получить все задачи.
	 *
	 * @param ctx контекст запроса
	 */
	private void getAllTasks(Context ctx) {
		List<Task> tasks = taskService.getAllTasks();
		ctx.json(tasks);
	}

	/**
	 * Получить одну задачу по ID.
	 *
	 * @param ctx контекст запроса
	 * @throws Exception если валидация или извлечение ID не удалось
	 */
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

	/**
	 * Создать новую задачу.
	 *
	 * @param ctx контекст запроса
	 * @throws Exception если валидация тела запроса не удалась
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

	/**
	 * Отметить задачу как выполненную.
	 *
	 * @param ctx контекст запроса
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
