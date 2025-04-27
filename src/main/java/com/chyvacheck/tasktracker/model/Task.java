
/**
 * @file Task.java
 * 
 * @description
 * Модель задачи (Task) в системе управления задачами.
 * Содержит основную информацию о задаче, включая её идентификатор, название, статус выполнения, дату создания и дедлайн.
 * 
 * @details
 * Особенности:
 * - Автоматическая генерация уникального ID при создании новой задачи
 * - Статус выполнения задачи (isComplete)
 * - Возможность устанавливать дедлайн выполнения
 * - Отметка времени создания задачи автоматически при создании
 * 
 * Примеры использования:
 * Task task = new Task("Fix login bug", LocalDateTime.now().plusDays(1));
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.model;

/**
 * ! java imports
 */
import java.util.concurrent.atomic.AtomicLong;
import java.time.LocalDateTime;

/**
 * Модель задачи в системе.
 */
public class Task {

	// * Статические свойства

	/**
	 * Статическое поле для генерации уникальных идентификаторов задач.
	 */
	static AtomicLong lastId = new AtomicLong(0);

	// * Свойства сущности

	/**
	 * Время создания задачи.
	 */
	private final LocalDateTime createdAt = LocalDateTime.now();

	/**
	 * Уникальный идентификатор задачи.
	 */
	private long id;

	/**
	 * Название задачи.
	 */
	private String title;

	/**
	 * Статус выполнения задачи (true — выполнена, false — не выполнена).
	 */
	private boolean isComplete = false;

	/**
	 * Дедлайн задачи (время до которого необходимо её завершить).
	 */
	private LocalDateTime deadline;

	/**
	 * * Конструкторы
	 */

	/**
	 * Конструктор задачи с указанием всех параметров.
	 *
	 * @param title      название задачи
	 * @param isComplete статус выполнения задачи
	 * @param deadline   дедлайн задачи
	 */
	public Task(String title, boolean isComplete, LocalDateTime deadline) {
		this.id = Task.generateNewId();
		this.title = title;
		this.isComplete = isComplete;
		this.deadline = deadline;
	}

	/**
	 * Конструктор задачи с указанием названия и дедлайна (статус по умолчанию — не
	 * выполнена).
	 *
	 * @param title    название задачи
	 * @param deadline дедлайн задачи
	 */
	public Task(String title, LocalDateTime deadline) {
		this(title, false, deadline);
	}

	/**
	 * Конструктор задачи с указанием названия и статуса (без дедлайна).
	 *
	 * @param title      название задачи
	 * @param isComplete статус выполнения задачи
	 */
	public Task(String title, boolean complete) {
		this(title, complete, null);
	}

	/**
	 * Конструктор задачи только с названием (без дедлайна, статус — не выполнена).
	 *
	 * @param title название задачи
	 */
	public Task(String title) {
		this(title, false, null);
	}

	// * Приватные методы

	/**
	 * Генерирует новый уникальный идентификатор задачи.
	 *
	 * @return новый уникальный идентификатор
	 */
	private static long generateNewId() {
		return lastId.getAndIncrement();
	}

	// * Геттеры и бизнес-методы

	/**
	 * Получить время создания задачи.
	 *
	 * @return время создания задачи
	 */
	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * Получить уникальный идентификатор задачи.
	 *
	 * @return ID задачи
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Получить название задачи.
	 *
	 * @return название задачи
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Отметить задачу как выполненную.
	 */
	public void markAsCompleted() {
		this.isComplete = true;
	}

	/**
	 * Проверить, выполнена ли задача.
	 *
	 * @return true если задача выполнена, иначе false
	 */
	public boolean isCompleted() {
		return this.isComplete;
	}

	/**
	 * Получить дедлайн задачи.
	 *
	 * @return дедлайн задачи
	 */
	public LocalDateTime getDeadline() {
		return this.deadline;
	}

	/**
	 * Переопределение метода toString для удобного отображения информации о задаче.
	 *
	 * @return строковое представление задачи
	 */
	@Override
	public String toString() {
		return "Task{id=" + id +
				", title='" + title + '\'' +
				", isComplete=" + isComplete +
				", createdAt=" + createdAt +
				'}';
	}

	/**
	 * Сбросить счётчик ID задач (используется для тестирования).
	 */
	public static void resetIdCounter() {
		lastId.set(0);
	}
}
