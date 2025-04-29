
/**
 * @file TaskService.java
 * 
 * @implements ITaskService
 * @extends BaseService
 * 
 * @description
 * Сервисный слой для работы с задачами (Task).
 * Отвечает за реализацию бизнес-логики при управлении задачами.
 * 
 * @details
 * Основные задачи:
 * - Делегирование операций репозиторию (ITaskRepository)
 * - Обработка бизнес-правил при создании и обновлении задач
 * 
 * Особенности:
 * - Поддержка создания задач с различными вариантами параметров
 * - Поддержка пометки задачи как выполненной
 * - Поддержка получения списка и отдельных задач
 * 
 * Используется контроллером TaskController для реализации API.
 * 
 * @example
 * TaskService service = new TaskService(new InMemoryTaskRepository());
 * service.createOneTask(\"Fix bug\", false, LocalDateTime.now().plusDays(1));
 * 
 * @see ITaskService
 * @see ITaskRepository
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.service;

/**
 * ! java imports
 */
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.time.LocalDateTime;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.core.base.BaseService;
import com.chyvacheck.tasktracker.core.response.service.ServiceProcessType;
import com.chyvacheck.tasktracker.core.response.service.ServiceResponse;
import com.chyvacheck.tasktracker.repository.ITaskRepository;
import com.chyvacheck.tasktracker.model.Task;

/**
 * Сервис для работы с задачами.
 */
public class TaskService extends BaseService implements ITaskService {

	private static TaskService instance;
	private final ITaskRepository repository;

	/**
	 * * Constructor
	 */

	/**
	 * Конструктор сервиса задач.
	 *
	 * @param repository репозиторий для работы с задачами
	 */
	protected TaskService(ITaskRepository repository) {
		super(TaskService.class);
		this.repository = repository;
	}

	/**
	 * * Static methods
	 */

	/**
	 * Инициализирует экземпляр TaskService.
	 * <p>
	 * Этот метод должен быть вызван только один раз при старте приложения.
	 * При повторной попытке инициализации будет выброшено исключение
	 * {@link IllegalStateException}.
	 *
	 * @param repository репозиторий задач, необходимый для работы сервиса
	 * @return инициализированный экземпляр TaskService
	 * @throws IllegalStateException если сервис уже был инициализирован
	 */
	public static TaskService initialize(ITaskRepository repository) {
		if (instance != null) {
			throw new IllegalStateException("TaskService already initialized!");
		}

		TaskService.instance = new TaskService(repository);
		return instance;
	}

	/**
	 * Получить текущий экземпляр TaskService.
	 * <p>
	 * Метод позволяет безопасно получить и использовать ранее инициализированный
	 * экземпляр сервиса.
	 *
	 * @return экземпляр TaskService
	 * @throws IllegalStateException если сервис ещё не был инициализирован
	 */
	public static TaskService getInstance() {
		if (TaskService.instance == null) {
			throw new IllegalStateException("TaskService is not initialized yet!");
		}
		return TaskService.instance;
	}

	/**
	 * * Methods
	 */

	/**
	 * ? Get
	 */

	/**
	 * Получить список всех задач.
	 *
	 * @return список всех задач
	 */
	public ServiceResponse<List<Task>> getAllTasks() {
		this.info("getAllTasks", null);
		List<Task> tasks = repository.getAllTask().stream()
				.sorted(Comparator.comparingLong(Task::getId))
				.toList();

		return new ServiceResponse<>(ServiceProcessType.FOUND, tasks);
	}

	/**
	 * Получить список задач по статусу выполнения.
	 *
	 * @param completed true — только выполненные задачи; false — только
	 *                  невыполненные
	 * @return список задач
	 */
	public ServiceResponse<List<Task>> getTasksByCompletionStatus(boolean completed) {
		List<Task> tasks = repository.getTasksByCompletionStatus(completed);

		return new ServiceResponse<>(ServiceProcessType.FOUND, tasks);
	}

	/**
	 * Получить задачу по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return задача, если найдена; иначе Optional.empty()
	 */
	public Optional<ServiceResponse<Task>> getOneTaskById(long id) {
		Optional<Task> taskOpt = repository.getOneTaskById(id);

		if (taskOpt.isEmpty()) {
			return Optional.empty();
		}

		Task task = taskOpt.get();

		return Optional.of(new ServiceResponse<>(ServiceProcessType.FOUND, task));
	}

	/**
	 * ? Create
	 */

	/**
	 * Создать новую задачу с указанными параметрами.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @param deadline дедлайн задачи
	 * @return созданная задача
	 */
	public ServiceResponse<Task> createOneTask(String title, boolean complete, LocalDateTime deadline) {
		Task task = repository.createOneTask(title, complete, deadline);

		return new ServiceResponse<>(ServiceProcessType.CREATED, task);
	}

	/**
	 * ? Update
	 */

	/**
	 * Попытаться завершить задачу по её идентификатору.
	 * <p>
	 * Если задача уже завершена, процесс будет отмечен как
	 * {@link ServiceProcessType#NOTHING}.
	 * Если задача успешно завершена в результате вызова, процесс будет
	 * {@link ServiceProcessType#UPDATED}.
	 * Если задача с указанным ID не найдена, возвращается {@link Optional#empty()}.
	 *
	 * @param id идентификатор задачи для завершения
	 * @return Optional с {@link ServiceResponse}, содержащим результат выполнения
	 *         операции
	 */
	@Override
	public Optional<ServiceResponse<Task>> completeOneTaskById(long id) {
		Optional<Task> taskOpt = repository.getOneTaskById(id);

		if (taskOpt.isEmpty()) {
			return Optional.empty();
		}

		Task task = taskOpt.get();

		if (task.isCompleted()) {
			return Optional.of(new ServiceResponse<>(ServiceProcessType.NOTHING, task));
		}

		task.markAsCompleted();
		repository.saveTask(task);

		return Optional.of(new ServiceResponse<>(ServiceProcessType.UPDATED, task));
	}

}
