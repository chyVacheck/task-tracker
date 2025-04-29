/**
 * @file ITaskRepository.java
 * 
 * @description
 * Интерфейс, описывающий методы репозитория для управления задачами (Task).
 * Определяет базовые CRUD-операции над задачами на уровне слоя доступа к данным.
 * 
 * @details
 * Основные задачи репозитория:
 * - Изоляция слоя доступа к данным
 * - Работа с сущностями задач
 * - Унифицированный контракт для различных реализаций (память, файлы, базы данных)
 * 
 * Методы:
 * - Получение списка всех задач
 * - Получение задач по статусу выполнения
 * - Получение задачи по ID
 * - Создание новой задачи
 * - Сохранение (обновление) задачи
 * 
 * @example
 * ITaskRepository repository = new InMemoryTaskRepository();
 * Task task = repository.createOneTask("New Task");
 * List<Task> tasks = repository.getAllTask();
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.repository;

/**
 * ! java imports
 */
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.model.Task;

/**
 * Интерфейс для работы с задачами на уровне репозитория.
 */
public interface ITaskRepository {

	/**
	 * Получить список всех задач.
	 *
	 * @return список задач
	 */
	List<Task> getAllTask();

	/**
	 * Получить список задач по статусу выполнения.
	 *
	 * @param completed true — только выполненные задачи; false — только
	 *                  невыполненные
	 * @return список задач, соответствующих статусу
	 */
	List<Task> getTasksByCompletionStatus(boolean completed);

	/**
	 * Получить задачу по её идентификатору.
	 *
	 * @param id идентификатор задачи
	 * @return задача, если найдена; иначе Optional.empty()
	 */
	Optional<Task> getOneTaskById(long id);

	/**
	 * Создать новую задачу с указанными параметрами.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @param deadline дедлайн выполнения задачи
	 * @return созданная задача
	 */
	Task createOneTask(String title, boolean complete, LocalDateTime deadline);

	/**
	 * Сохранить или обновить задачу.
	 *
	 * @param task задача для сохранения
	 */
	void saveTask(Task task);

	/**
	 * Удалить задачу по её идентификатору.
	 *
	 * @param id идентификатор задачи
	 * @return удалённая задача, если была найдена; иначе Optional.empty()
	 */
	Optional<Task> deleteTaskById(long id);
}
