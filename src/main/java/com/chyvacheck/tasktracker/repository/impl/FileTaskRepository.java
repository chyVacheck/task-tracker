
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

	private static FileTaskRepository instance;

	/**
	 * * Constructor
	 */

	/**
	 * Конструктор файлового репозитория задач.
	 * На данный момент дополнительная инициализация не требуется.
	 */
	protected FileTaskRepository() {
		super(FileTaskRepository.class);
	}

	/**
	 * * Static methods
	 */

	/**
	 * Инициализирует экземпляр FileTaskRepository.
	 * <p>
	 * Этот метод должен быть вызван только один раз при старте приложения.
	 * При повторной попытке инициализации будет выброшено исключение
	 * {@link IllegalStateException}.
	 *
	 * @return инициализированный экземпляр FileTaskRepository
	 * @throws IllegalStateException если репозиторий уже был инициализирован
	 */
	public static FileTaskRepository initialize() {
		if (FileTaskRepository.instance != null) {
			throw new IllegalStateException("FileTaskRepository already initialized!");
		}
		FileTaskRepository.instance = new FileTaskRepository();
		return FileTaskRepository.instance;
	}

	/**
	 * Получить текущий экземпляр FileTaskRepository.
	 * <p>
	 * Метод позволяет безопасно получить и использовать ранее инициализированный
	 * экземпляр репозитория.
	 *
	 * @return экземпляр FileTaskRepository
	 * @throws IllegalStateException если репозиторий ещё не был инициализирован
	 */
	public static FileTaskRepository getInstance() {
		if (FileTaskRepository.instance == null) {
			throw new IllegalStateException("FileTaskRepository is not initialized yet!");
		}
		return FileTaskRepository.instance;
	}

	/**
	 * * Methods
	 */

	/**
	 * ? Get
	 */

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
	 * ? Create
	 */

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
	 * ? Update
	 */

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

	/**
	 * Удаляет задачу из файловой системы по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return удалённая задача, если была; иначе Optional.empty()
	 */
	@Override
	public Optional<Task> deleteTaskById(long id) {
		Optional<Task> existing = TaskFileStorage.loadTaskById(id);

		if (existing.isEmpty()) {
			return Optional.empty();
		}

		TaskFileStorage.deleteTaskById(id);
		return existing;
	}
}
