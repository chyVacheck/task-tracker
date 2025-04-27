/**
 * @description Реализация интерфейса репозитория
 */

package com.chyvacheck.tasktracker.repository.impl;

/**
 * ! java imports
 */
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.model.Task;
import com.chyvacheck.tasktracker.repository.ITaskRepository;

public class InMemoryTaskRepository implements ITaskRepository {

	private Map<Long, Task> tasks = new HashMap<Long, Task>();

	public List<Task> getAllTask() {
		return new ArrayList<Task>(tasks.values());
	}

	public Optional<Task> getOneTaskById(long id) {
		return Optional.ofNullable(tasks.get(id));
	}

	public Task createOneTask(String title, boolean complete, LocalDateTime deadline) {
		Task task = new Task(title, complete, deadline);
		tasks.put(task.getId(), task);
		return task;
	}

	public Task createOneTask(String title, boolean complete) {
		return this.createOneTask(title, complete, null);
	}

	public Task createOneTask(String title) {
		return this.createOneTask(title, false, null);
	}

	public Optional<Task> completeOneTaskById(long id) {
		Task task = tasks.get(id);

		if (task == null) {
			return Optional.empty();
		}

		task.markAsCompleted();
		return Optional.of(task);
	}
}
