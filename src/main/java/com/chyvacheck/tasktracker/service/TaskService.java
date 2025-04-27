
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
import java.time.LocalDateTime;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.core.base.BaseService;
import com.chyvacheck.tasktracker.repository.ITaskRepository;
import com.chyvacheck.tasktracker.model.Task;

/**
 * Сервис для работы с задачами.
 */
public class TaskService extends BaseService implements ITaskService {
	private final ITaskRepository repository;

	/**
	 * Конструктор сервиса задач.
	 *
	 * @param repository репозиторий для работы с задачами
	 */
	public TaskService(ITaskRepository repository) {
		this.repository = repository;
	}

	/**
	 * * Методы
	 */

	/**
	 * Получить список всех задач.
	 *
	 * @return список всех задач
	 */
	public List<Task> getAllTasks() {
		return repository.getAllTask();
	}

	/**
	 * Получить задачу по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return задача, если найдена; иначе Optional.empty()
	 */
	public Optional<Task> getOneTaskById(long id) {
		return repository.getOneTaskById(id);
	}

	/**
	 * Создать новую задачу с указанными параметрами.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @param deadline дедлайн задачи
	 * @return созданная задача
	 */
	public Task createOneTask(String title, boolean complete, LocalDateTime deadline) {
		return repository.createOneTask(title, complete, deadline);
	}

	/**
	 * Создать новую задачу без дедлайна.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @return созданная задача
	 */
	public Task createOneTask(String title, boolean complete) {
		return repository.createOneTask(title, complete);
	}

	/**
	 * Создать новую задачу со статусом \"не выполнена\" и с дедлайном.
	 *
	 * @param title    название задачи
	 * @param deadline дедлайн задачи
	 * @return созданная задача
	 */
	public Task createOneTask(String title, LocalDateTime deadline) {
		return repository.createOneTask(title, false, deadline);
	}

	/**
	 * Создать новую задачу без дедлайна и со статусом \"не выполнена\".
	 *
	 * @param title название задачи
	 * @return созданная задача
	 */
	public Task createOneTask(String title) {
		return repository.createOneTask(title);
	}

	/**
	 * Пометить задачу как выполненную по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return обновлённая задача, если найдена; иначе Optional.empty()
	 */
	public Optional<Task> completeOneTaskById(long id) {
		return repository.completeOneTaskById(id);
	}

}
