
/**
 * @description Модель задачи
 */

package com.chyvacheck.tasktracker.model;

/**
 * ! java imports
 */
import java.util.concurrent.atomic.AtomicLong;
import java.time.LocalDateTime;

public class Task {

	/**
	 * * Статические свойства
	 */

	static AtomicLong lastId = new AtomicLong(0);

	/**
	 * * Свойства отдельной сущности
	 */

	/**
	 * @description время создания задачи
	 */
	private final LocalDateTime createdAt = LocalDateTime.now();
	/**
	 * @description уникальный id задачи
	 */
	private long id;
	/**
	 * @description название задачи
	 */
	private String title;
	/**
	 * @description статус задачи - выполнена либо нет
	 */
	private boolean isComplete = false;
	/**
	 * @description время до которого необходимо выполнить задачу
	 */
	private LocalDateTime deadline;

	/**
	 * * Конструкторы
	 */

	/**
	 * @param title      - название задачи
	 * @param isComplete - выполнена ли задача
	 * @param deadline   - время до которого необходимо выполнить задачу
	 */
	public Task(String title, boolean isComplete, LocalDateTime deadline) {
		this.id = Task.generateNewId();
		this.title = title;
		this.isComplete = isComplete;
		this.deadline = deadline;
	}

	/**
	 * @param title    - название задачи
	 * @param deadline - время до которого необходимо выполнить задачу
	 */
	public Task(String title, LocalDateTime deadline) {
		this(title, false, deadline);
	}

	/**
	 * @param title      - название задачи
	 * @param isComplete - выполнена ли задача
	 */
	public Task(String title, boolean complete) {
		this(title, complete, null);
	}

	/**
	 * @param title - название задачи
	 */
	public Task(String title) {
		this(title, false, null);
	}

	/**
	 * * Приватные методы
	 */

	static private long generateNewId() {
		return lastId.getAndIncrement();
	}

	/**
	 * * Методы
	 */

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public void markAsCompleted() {
		this.isComplete = true;
	}

	public boolean isCompleted() {
		return this.isComplete;
	}

	public LocalDateTime getDeadline() {
		return this.deadline;
	}

	@Override
	public String toString() {
		return "Task{id=" + id + ", title='" + title + "', isComplete=" + isComplete + "', createAt="
				+ createdAt.toString()
				+ "}";
	}

	public static void resetIdCounter() {
		lastId.set(0);
	}
}
