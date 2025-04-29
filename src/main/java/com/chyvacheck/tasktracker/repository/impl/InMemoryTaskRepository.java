
/**
 * @file InMemoryTaskRepository.java
 * 
 * @implements ITaskRepository
 * @extends BaseRepository
 * 
 * @description
 * Реализация репозитория задач (Task) с хранением данных в памяти.
 * Используется для хранения задач в процессе работы приложения без постоянного сохранения в файловую систему или базу данных.
 * 
 * @details
 * Особенности реализации:
 * - Данные задач хранятся в HashMap (имитируется поведение базы данных)
 * - ID задач автоматически генерируются при создании
 * - Поддерживаются базовые операции CRUD:
 *   - Получение списка всех задач
 *   - Получение задач по статусу выполнения
 *   - Получение задачи по ID
 *   - Создание новой задачи
 *   - Сохранение обновлений задачи
 * 
 * Используется на этапе разработки, тестирования или как in-memory альтернатива реальному репозиторию.
 * 
 * @example
 * InMemoryTaskRepository repo = new InMemoryTaskRepository();
 * Task task = repo.createOneTask("New task");
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

	InMemoryTaskRepository() {
		super(InMemoryTaskRepository.class);
	}

	/**
	 * Получить список всех задач.
	 *
	 * @return список всех задач
	 */
	@Override
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
	@Override
	public List<Task> getTasksByCompletionStatus(boolean completed) {
		return tasks.values().stream()
				.filter(task -> task.isCompleted() == completed)
				.toList();
	}

	/**
	 * Получает задачу по её идентификатору.
	 *
	 * @param id идентификатор задачи
	 * @return задача в Optional, если найдена; иначе Optional.empty()
	 */
	@Override
	public Optional<Task> getOneTaskById(long id) {
		return Optional.ofNullable(tasks.get(id));
	}

	/**
	 * Создаёт новую задачу и сохраняет её в память.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @param deadline срок выполнения задачи
	 * @return созданная задача
	 */
	@Override
	public Task createOneTask(String title, boolean complete, LocalDateTime deadline) {
		Task task = new Task(title, complete, deadline);
		saveTask(task);
		return task;
	}

	/**
	 * Сохраняет задачу в память.
	 *
	 * @param task задача для сохранения
	 */
	@Override
	public void saveTask(Task task) {
		tasks.put(task.getId(), task);
	}
}
