/**
 * @description Интерфейс описывающий методы репозитория
 */

package com.chyvacheck.tasktracker.repository;

/**
 * ! java imports
 */
import java.util.List;
import java.util.Optional;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.model.Task;

public interface ITaskRepository {

	public List<Task> getAllTask();

	public Optional<Task> getOneTaskById(long id);

	public Task createOneTask(String title, boolean complete);

	public Task createOneTask(String title);

	public Optional<Task> completeOneTaskById(long id);

}
