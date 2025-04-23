/**
 * @description Сервис 
 */

package com.chyvacheck.tasktracker.service;

/**
 * ! java imports
 */
import java.util.List;
import java.util.Optional;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.model.Task;
import com.chyvacheck.tasktracker.repository.ITaskRepository;

public class TaskService implements ITaskService {
	private final ITaskRepository repository;

	/**
	 * * Конструктор
	 */
	public TaskService(ITaskRepository repository) {
		this.repository = repository;
	}

	/**
	 * * Методы
	 */

	public List<Task> getAllTasks() {
		return repository.getAllTask();
	}

	public Optional<Task> getOneTaskById(long id) {
		return repository.getOneTaskById(id);
	}

	public Task createOneTask(String title) {
		return repository.createOneTask(title);
	}

	public Task createOneTask(String title, boolean complete) {
		return repository.createOneTask(title, complete);
	}

	public Optional<Task> completeOneTaskById(long id) {
		return repository.completeOneTaskById(id);
	}

}
