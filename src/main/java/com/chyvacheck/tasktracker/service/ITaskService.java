/**
 * @file ITaskService.java
 * 
 * @description
 * Интерфейс, описывающий методы сервисного слоя для управления задачами (Task).
 * Определяет бизнес-операции для работы с задачами через сервис.
 * 
 * @details
 * Основные задачи сервиса:
 * - Инкапсуляция бизнес-логики управления задачами
 * - Делегирование операций репозиторию
 * 
 * Методы:
 * - Получение списка всех задач
 * - Получение одной задачи по ID
 * - Создание новой задачи с разными параметрами
 * - Завершение задачи по ID
 * 
 * @example
 * ITaskService service = new TaskService(new InMemoryTaskRepository());
 * service.createOneTask("Implement validation logic");
 * 
 * @see ITaskRepository
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
 * ! my imports
 */
import com.chyvacheck.tasktracker.model.Task;
import com.chyvacheck.tasktracker.repository.ITaskRepository;

/**
 * Интерфейс для работы с задачами на уровне сервисного слоя.
 */
public interface ITaskService {

	/**
	 * Получить список всех задач.
	 *
	 * @return список задач
	 */
	List<Task> getAllTasks();

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
	 * Создать новую задачу без дедлайна.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @return созданная задача
	 */
	Task createOneTask(String title, boolean complete);

	/**
	 * Создать новую задачу со статусом \"не выполнена\" и с дедлайном.
	 *
	 * @param title    название задачи
	 * @param deadline дедлайн выполнения задачи
	 * @return созданная задача
	 */
	Task createOneTask(String title, LocalDateTime deadline);

	/**
	 * Создать новую задачу без дедлайна и со статусом \"не выполнена\".
	 *
	 * @param title название задачи
	 * @return созданная задача
	 */
	Task createOneTask(String title);

	/**
	 * Пометить задачу как выполненную по её ID.
	 * 
	 * @param id идентификатор задачи
	 * @return обновлённая задача, если найдена; иначе Optional.empty()
	 */
	Optional<Task> completeOneTaskById(long id);
}