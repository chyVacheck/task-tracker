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
import com.chyvacheck.tasktracker.core.response.service.ServiceResponse;
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
	ServiceResponse<List<Task>> getAllTasks();

	/**
	 * Получить задачу по её идентификатору.
	 *
	 * @param id идентификатор задачи
	 * @return задача, если найдена; иначе Optional.empty()
	 */
	Optional<ServiceResponse<Task>> getOneTaskById(long id);

	/**
	 * Получить список задач по статусу выполнения.
	 *
	 * @param completed true — только выполненные задачи; false — только
	 *                  невыполненные
	 * @return список задач
	 */
	ServiceResponse<List<Task>> getTasksByCompletionStatus(boolean completed);

	/**
	 * Создать новую задачу с указанными параметрами.
	 *
	 * @param title    название задачи
	 * @param complete статус выполнения задачи
	 * @param deadline дедлайн выполнения задачи
	 * @return созданная задача
	 */
	ServiceResponse<Task> createOneTask(String title, boolean complete, LocalDateTime deadline);

	/**
	 * Завершить задачу по ID с проверкой состояния.
	 *
	 * @param id идентификатор задачи
	 * @return результат выполнения операции с информацией о процессе
	 */
	Optional<ServiceResponse<Task>> completeOneTaskById(long id);

	/**
	 * Удалить задачу по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return Optional содержащий удалённую задачу в обёртке ServiceResponse, если
	 *         задача была найдена; иначе Optional.empty()
	 */
	public Optional<ServiceResponse<Task>> deleteOneTaskById(long id);
}