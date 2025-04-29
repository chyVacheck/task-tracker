
/**
 * @file TaskController.java
 * 
 * @extends BaseController
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
 * - GET /tasks/completed — получить выполненные задачи
 * - GET /tasks/incomplete — получить не выполненные задачи
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
import com.chyvacheck.tasktracker.core.response.http.HttpStatusCode;
import com.chyvacheck.tasktracker.core.response.http.SuccessResponse;
import com.chyvacheck.tasktracker.core.response.service.ServiceProcessType;
import com.chyvacheck.tasktracker.core.response.service.ServiceResponse;
import com.chyvacheck.tasktracker.core.routes.Routable;
import com.chyvacheck.tasktracker.core.routes.RouteDefinition;
import com.chyvacheck.tasktracker.core.base.BaseController;
import com.chyvacheck.tasktracker.middleware.validate.ValidateMiddleware;
import com.chyvacheck.tasktracker.controller.dto.TaskIdPathDto;
import com.chyvacheck.tasktracker.controller.dto.TaskCreateDto;
import com.chyvacheck.tasktracker.service.ITaskService;
import com.chyvacheck.tasktracker.model.Task;

/**
 * Контроллер для работы с задачами (Task).
 */
public class TaskController extends BaseController implements Routable {

	private static TaskController instance;
	private final ITaskService taskService;
	private final ValidateMiddleware validateMiddleware = ValidateMiddleware.getInstance();

	/**
	 * * Constructor
	 */

	/**
	 * Конструктор TaskController.
	 * 
	 * @param taskService сервис для работы с задачами
	 */
	protected TaskController(ITaskService taskService) {
		super(TaskController.class, "/tasks"); // Устанавливаем базовый путь для маршрутов задач
		this.taskService = taskService;
	}

	/**
	 * * Static methods
	 */

	/**
	 * Инициализирует экземпляр TaskController.
	 * <p>
	 * Этот метод должен быть вызван только один раз при старте приложения.
	 * При повторной попытке инициализации будет выброшено исключение
	 * {@link IllegalStateException}.
	 *
	 * @param taskService сервис задач, необходимый для работы контроллера
	 * @return инициализированный экземпляр TaskController
	 * @throws IllegalStateException если контроллер уже был инициализирован
	 */
	public static TaskController initialize(ITaskService taskService) {
		if (instance != null) {
			throw new IllegalStateException("TaskController already initialized!");
		}

		TaskController.instance = new TaskController(taskService);
		return instance;
	}

	/**
	 * Получить текущий экземпляр TaskController.
	 * <p>
	 * Метод позволяет безопасно получить и использовать ранее инициализированный
	 * экземпляр контроллера.
	 *
	 * @return экземпляр TaskController
	 * @throws IllegalStateException если контроллер ещё не был инициализирован
	 */
	public static TaskController getInstance() {
		if (TaskController.instance == null) {
			throw new IllegalStateException("TaskController is not initialized yet!");
		}
		return TaskController.instance;
	}

	/**
	 * * Methods
	 */

	@Override
	public List<RouteDefinition> routes() {
		return List.of(
				new RouteDefinition("GET", basePath, this::getAllTasks),
				new RouteDefinition("POST", basePath, this::createOneTask),
				new RouteDefinition("GET", basePath + "/completed", this::getCompletedTasks),
				new RouteDefinition("GET", basePath + "/incomplete", this::getIncompleteTasks),
				new RouteDefinition("GET", basePath + "/{id}", this::getOneTaskById),
				new RouteDefinition("PATCH", basePath + "/{id}", this::completeOneTaskById),
				new RouteDefinition("DELETE", basePath + "/{id}", this::deleteOneTaskById));
	}

	/**
	 * ? Get
	 */

	/**
	 * Получить список всех задач.
	 * <p>
	 * Возвращает статус {@code 200 OK} и список всех задач.
	 *
	 * @param ctx Контекст HTTP-запроса Javalin
	 */
	private void getAllTasks(Context ctx) {

		ServiceResponse<List<Task>> resultOpt = taskService.getAllTasks();

		ctx.json(new SuccessResponse(
				HttpStatusCode.OK,
				"All tasks fetched successfully",
				resultOpt.getData(),
				null));
	}

	/**
	 * Получить список всех выполненных задач.
	 * <p>
	 * Возвращает статус {@code 200 OK} и список задач, помеченных как завершённые.
	 *
	 * @param ctx Контекст HTTP-запроса Javalin
	 */
	private void getCompletedTasks(Context ctx) {

		ServiceResponse<List<Task>> resultOpt = taskService.getTasksByCompletionStatus(true);

		ctx.json(new SuccessResponse(
				HttpStatusCode.OK,
				"Complete tasks fetched successfully",
				resultOpt.getData(),
				null));
	}

	/**
	 * Получить список всех невыполненных задач.
	 * <p>
	 * Возвращает статус {@code 200 OK} и список задач, которые ещё не завершены.
	 *
	 * @param ctx Контекст HTTP-запроса Javalin
	 */
	private void getIncompleteTasks(Context ctx) {

		ServiceResponse<List<Task>> resultOpt = taskService.getTasksByCompletionStatus(false);

		ctx.json(new SuccessResponse(
				HttpStatusCode.OK,
				"Incomplete tasks fetched successfully",
				resultOpt.getData(),
				null));
	}

	/**
	 * Получить одну задачу по её идентификатору.
	 * <p>
	 * Если задача найдена, возвращает статус {@code 200 OK} и данные задачи.
	 * Если задача с указанным ID не найдена, выбрасывает исключение
	 * {@link NotFoundTaskException}.
	 * <p>
	 * Использует валидацию параметров пути через {@link ValidateMiddleware}.
	 *
	 * @param ctx Контекст HTTP-запроса Javalin
	 * @throws Exception Если валидация параметров запроса завершается с ошибкой
	 */
	private void getOneTaskById(Context ctx) throws Exception {

		TaskIdPathDto dto = this.validateMiddleware.fromPath(ctx, TaskIdPathDto.class);

		Optional<ServiceResponse<Task>> resultOpt = taskService.getOneTaskById(dto.getId());

		if (resultOpt.isEmpty()) {
			throw new NotFoundTaskException("Task with this id not found", Map.of("id", dto.getId()));
		}

		ServiceResponse<Task> result = resultOpt.get();

		ctx.json(new SuccessResponse(
				HttpStatusCode.OK,
				"Tasks fetched successfully",
				result.getData(),
				Map.of("id", dto.getId())));

	}

	/**
	 * ? Create
	 */

	/**
	 * Создать новую задачу.
	 * <p>
	 * При успешном создании задачи возвращает статус {@code 201 Created} и данные
	 * новой задачи.
	 * <p>
	 * Использует валидацию тела запроса через {@link ValidateMiddleware}.
	 *
	 * @param ctx Контекст HTTP-запроса Javalin
	 * @throws Exception Если валидация тела запроса завершается с ошибкой
	 */
	private void createOneTask(Context ctx) throws Exception {

		TaskCreateDto dto = this.validateMiddleware.fromBody(ctx, TaskCreateDto.class);

		ServiceResponse<Task> resultOpt = taskService.createOneTask(dto.getTitle(), false, dto.getDeadline());

		ctx.status(HttpStatusCode.CREATED.getCode());
		ctx.json(new SuccessResponse(
				HttpStatusCode.CREATED,
				"Task created successfully",
				resultOpt.getData(),
				null));

	}

	/**
	 * ? Update
	 */

	/**
	 * Завершить задачу по её идентификатору.
	 * <p>
	 * Выполняет попытку завершения задачи:
	 * - Если задача уже была завершена ранее, возвращает статус {@code 200 OK} и
	 * сообщение "Task is already completed".
	 * - Если задача была успешно завершена в результате запроса, возвращает статус
	 * {@code 201 Created} и сообщение "Task marked as completed successfully".
	 * - Если задача с указанным ID не найдена, выбрасывает исключение
	 * {@link NotFoundTaskException}.
	 * - В случае непредвиденного типа процесса возвращает
	 * {@code 500 Internal Server Error}.
	 * <p>
	 * Использует валидацию параметров пути через {@link ValidateMiddleware}.
	 *
	 * @param ctx Контекст HTTP-запроса Javalin
	 * @throws Exception Если валидация параметров запроса или выполнение операции
	 *                   завершается с ошибкой
	 */
	private void completeOneTaskById(Context ctx) throws Exception {

		TaskIdPathDto dto = this.validateMiddleware.fromPath(ctx, TaskIdPathDto.class);

		Optional<ServiceResponse<Task>> resultOpt = taskService.completeOneTaskById(dto.getId());

		if (resultOpt.isEmpty()) {
			throw new NotFoundTaskException("Task with this id not found", Map.of("id", dto.getId()));
		}

		ServiceResponse<Task> result = resultOpt.get();

		if (result.getProcess() == ServiceProcessType.NOTHING) {
			// Задача уже была выполнена
			ctx.status(HttpStatusCode.OK.getCode());
			ctx.json(new SuccessResponse(
					HttpStatusCode.OK,
					"Task is already completed",
					result.getData(),
					Map.of("id", dto.getId())));
		} else if (result.getProcess() == ServiceProcessType.UPDATED) {
			// Задача успешно завершена
			ctx.status(HttpStatusCode.CREATED.getCode());
			ctx.json(new SuccessResponse(
					HttpStatusCode.CREATED,
					"Task marked as completed successfully",
					result.getData(),
					Map.of("id", dto.getId())));
		} else {
			// Непредвиденное поведение (на будущее защита)
			ctx.status(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode());
			ctx.json(new SuccessResponse(
					HttpStatusCode.INTERNAL_SERVER_ERROR,
					"Unknown process during task completion",
					result.getData(),
					Map.of("id", dto.getId())));
		}

	}

	/**
	 * ? Delete
	 */

	/**
	 * Удалить задачу по её идентификатору.
	 *
	 * @param ctx контекст запроса
	 * @throws Exception если валидация или удаление завершились ошибкой
	 */
	private void deleteOneTaskById(Context ctx) throws Exception {

		TaskIdPathDto dto = ValidateMiddleware.getInstance().fromPath(ctx, TaskIdPathDto.class);

		Optional<ServiceResponse<Task>> resultOpt = taskService.deleteOneTaskById(dto.getId());

		if (resultOpt.isEmpty()) {
			throw new NotFoundTaskException("Task with this id not found", Map.of("id", dto.getId()));
		}

		ServiceResponse<Task> result = resultOpt.get();

		ctx.status(HttpStatusCode.OK.getCode());
		ctx.json(new SuccessResponse(
				HttpStatusCode.OK,
				"Task deleted successfully",
				result.getData(),
				Map.of("id", dto.getId())));
	}
}
