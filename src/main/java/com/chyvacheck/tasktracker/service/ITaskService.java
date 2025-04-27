/**
 * @description Интерфейс описывающий методы сервиса
 */

package com.chyvacheck.tasktracker.service;

/**
 * ! java imports
 */
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.model.Task;

public interface ITaskService {

	public List<Task> getAllTasks();

	public Optional<Task> getOneTaskById(long id);

	public Task createOneTask(String title, boolean complete, LocalDateTime deadline);

	public Task createOneTask(String title, boolean complete);

	public Task createOneTask(String title, LocalDateTime deadline);

	public Task createOneTask(String title);

	public Optional<Task> completeOneTaskById(long id);
}