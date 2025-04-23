
/**
 * @description Модель задачи
 */

package com.chyvacheck.tasktracker.model;

/**
 * ! java imports
 */
import java.util.concurrent.atomic.AtomicLong;

public class Task {

	/**
	 * * Статические свойства
	 */

	static AtomicLong lastId = new AtomicLong(0);

	/**
	 * * Свойства отдельной сущности
	 */

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
	private boolean complete = false;

	/**
	 * * Конструкторы
	 */

	/**
	 * Конструктор,
	 * 
	 * @param title - название задачи
	 */
	public Task(String title) {
		this.id = Task.generateNewId();
		this.title = title;
	}

	/**
	 * 
	 * @param title    - название задачи
	 * @param complete - выполнена ли задача
	 */
	public Task(String title, boolean complete) {
		this.id = Task.generateNewId();
		this.title = title;
		this.complete = complete;
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

	public long getId() {
		return id;
	}

	public String getTitle() {
		return this.title;
	}

	public void complete() {
		this.complete = true;
	}

	public boolean isComplete() {
		return this.complete;
	}

	@Override
	public String toString() {
		return "Task{id=" + id + ", title='" + title + "', complete=" + complete + "}";
	}

	public static void resetIdCounter() {
		lastId.set(0);
	}
}
