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
 * 
 * Методы:
 * - Получение списка всех задач
 * - Получение задачи по ID
 * - Создание новой задачи с разными параметрами
 * - Завершение задачи по ID
 * 
 * @example
 * ITaskRepository repository = new InMemoryTaskRepository();
 * Task task = repository.createOneTask("New Task");
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
	 * Создать новую задачу без дедлайна и со статусом \"не выполнена\".
	 * 
	 * @param title название задачи
	 * @return созданная задача
	 */
	Task createOneTask(String title);

	/**
	 * Отметить задачу как выполненную по её ID.
	 *
	 * @param id идентификатор задачи
	 * @return обновлённая задача, если найдена; иначе Optional.empty()
	 */
	Optional<Task> completeOneTaskById(long id);
}