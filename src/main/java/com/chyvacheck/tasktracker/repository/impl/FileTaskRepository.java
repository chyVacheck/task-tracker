
/**
 * @file FileTaskRepository.java
 * 
 * @description
 * Репозиторий для работы с задачами через файловую систему.
 * Хранит каждую задачу в отдельном JSON-файле.
 * 
 * @see TaskFileStorage
 * @see ITaskRepository
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.repository.impl;

/**
 * ! java imports
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.model.Task;
import com.chyvacheck.tasktracker.repository.ITaskRepository;
import com.chyvacheck.tasktracker.filesystem.TaskFileStorage;
import com.chyvacheck.tasktracker.core.base.BaseRepository;

/**
 * Реализация репозитория задач, работающая через файловую систему.
 * Использует {@link TaskFileStorage} для хранения каждой задачи в отдельном
 * JSON-файле.
 */
public class FileTaskRepository extends BaseRepository implements ITaskRepository {

	/**
	 * Конструктор файлового репозитория задач.
	 * На данный момент дополнительная инициализация не требуется.
	 */
	public FileTaskRepository() {
		// возможно в будущем нужна будет инициализация
	}

	/**
	 * Получить все задачи, хранящиеся в файловой системе.
	 *
	 * @return список всех задач
	 */
	@Override
	public List<Task> getAllTask() {
		return TaskFileStorage.loadAllTasks();
	}

	/**
	 * Получить список задач по статусу выполнения.
	 *
	 * @param completed true — только выполненные задачи; false — только
	 *                  невыполненные
	 * @return список задач, соответствующих указанному статусу выполнения
	 */
	@Override
	public List<Task> getTasksByCompletionStatus(boolean completed) {
		return TaskFileStorage.loadAllTasks().stream()
				.filter(task -> task.isCompleted() == completed)
				.toList();
	}

	/**
	 * Найти задачу по её идентификатору.
	 *
	 * @param id идентификатор задачи
	 * @return {@link Optional} содержащий задачу, либо пустой {@link Optional},
	 *         если задача не найдена
	 */
	@Override
	public Optional<Task> getOneTaskById(long id) {
		return TaskFileStorage.loadTaskById(id);
	}

	/**
	 * Создать новую задачу и сохранить её в файловую систему.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @param deadline дедлайн задачи (может быть {@code null})
	 * @return созданная задача
	 */
	@Override
	public Task createOneTask(String title, boolean complete, LocalDateTime deadline) {
		Task task = new Task(title, complete, deadline);
		saveTask(task);
		return task;
	}

	/**
	 * Сохранить задачу в файловую систему.
	 * Если задача уже существует (по ID), она будет перезаписана.
	 *
	 * @param task задача для сохранения
	 */
	@Override
	public void saveTask(Task task) {
		TaskFileStorage.saveTask(task);
	}
}
