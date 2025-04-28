
/**
 * @file InMemoryTaskRepository.java
 * 
 * @implements ITaskRepository
 * @extends BaseRepository
 * 
 * @description
 * Реализация репозитория задач (Task) с хранением данных в памяти.
 * Используется для хранения задач в процессе работы приложения без постоянного сохранения в базу данных.
 * 
 * @details
 * Особенности реализации:
 * - Данные задач хранятся в HashMap (имитируется поведение базы данных)
 * - ID задач автоматически генерируются при создании
 * - Поддерживаются базовые операции CRUD:
 *   - Получение списка всех задач
 *   - Получение задачи по ID
 *   - Создание новой задачи
 *   - Завершение задачи
 * 
 * Используется на начальном этапе разработки или для тестирования без полноценной базы данных.
 * 
 * @example
 * InMemoryTaskRepository repo = new InMemoryTaskRepository();
 * Task task = repo.createOneTask(\"New task\");
 * List<Task> tasks = repo.getAllTask();
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.repository.impl;

/**
 * ! java imports
 */
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.core.base.BaseRepository;
import com.chyvacheck.tasktracker.repository.ITaskRepository;
import com.chyvacheck.tasktracker.model.Task;

/**
 * Реализация репозитория задач с хранением в памяти.
 */
public class InMemoryTaskRepository extends BaseRepository implements ITaskRepository {

	private Map<Long, Task> tasks = new HashMap<Long, Task>();

	/**
	 * Получить список всех задач.
	 *
	 * @return список всех задач
	 */
	public List<Task> getAllTask() {
		return new ArrayList<Task>(tasks.values());
	}

	/**
	 * Получить список задач по статусу выполнения.
	 *
	 * @param completed true — только выполненные задачи; false — только
	 *                  невыполненные
	 * @return список задач, соответствующих статусу
	 */
	public List<Task> getTasksByCompletionStatus(boolean completed) {
		return tasks.values().stream()
				.filter(task -> task.isCompleted() == completed)
				.toList();
	}

	/**
	 * Получить задачу по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return задача, если найдена; иначе Optional.empty()
	 */
	public Optional<Task> getOneTaskById(long id) {
		return Optional.ofNullable(tasks.get(id));
	}

	/**
	 * Создать новую задачу с дедлайном.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @param deadline дедлайн задачи
	 * @return созданная задача
	 */
	public Task createOneTask(String title, boolean complete, LocalDateTime deadline) {
		Task task = new Task(title, complete, deadline);
		tasks.put(task.getId(), task);
		return task;
	}

	/**
	 * Создать новую задачу без дедлайна с указанным статусом выполнения.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @return созданная задача
	 */
	public Task createOneTask(String title, boolean complete) {
		return this.createOneTask(title, complete, null);
	}

	/**
	 * Создать новую задачу без дедлайна и со статусом \"не выполнена\".
	 *
	 * @param title название задачи
	 * @return созданная задача
	 */
	public Task createOneTask(String title) {
		return this.createOneTask(title, false, null);
	}

	/**
	 * Отметить задачу как выполненную по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return обновлённая задача, если найдена; иначе Optional.empty()
	 */
	public Optional<Task> completeOneTaskById(long id) {
		Task task = tasks.get(id);

		if (task == null) {
			return Optional.empty();
		}

		task.markAsCompleted();
		return Optional.of(task);
	}
}
