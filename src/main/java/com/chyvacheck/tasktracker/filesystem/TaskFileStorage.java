
/**
 * @file TaskFileStorage.java
 * 
 * @description
 * Класс для работы с файловым хранилищем задач.
 * Отвечает за чтение, сохранение и удаление задач через файловую систему.
 * Каждая задача хранится в отдельном JSON-файле с именем {@code {id}.json}.
 * 
 * @details
 * Расположение файлов: {@code /data/tasks/}
 * 
 * Использует Jackson для сериализации и десериализации объектов.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.filesystem;

/**
 * ! java imports
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

/**
 * ! lib imports
 */
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.system.ObjectMapperProvider;
import com.chyvacheck.tasktracker.model.Task;

public class TaskFileStorage {

	private static final String STORAGE_DIR = "data/tasks/";
	private static final ObjectMapper objectMapper = ObjectMapperProvider.get();

	/**
	 * Загрузить все задачи из файловой системы.
	 *
	 * @return список всех задач
	 */
	public static List<Task> loadAllTasks() {
		List<Task> tasks = new ArrayList<>();
		File dir = new File(STORAGE_DIR);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
		if (files == null) {
			return tasks;
		}

		for (File file : files) {
			try {
				Task task = objectMapper.readValue(file, Task.class);
				tasks.add(task);
			} catch (IOException e) {
				System.err.println("Ошибка при чтении файла задачи: " + file.getName());
				e.printStackTrace();
			}
		}

		return tasks;
	}

	/**
	 * Загрузить задачу по её идентификатору.
	 *
	 * @param id идентификатор задачи
	 * @return задача, обёрнутая в Optional, или пустой Optional, если задача не
	 *         найдена
	 */
	public static Optional<Task> loadTaskById(long id) {
		File file = new File(STORAGE_DIR + id + ".json");

		if (!file.exists()) {
			return Optional.empty();
		}

		try {
			Task task = objectMapper.readValue(file, Task.class);
			return Optional.of(task);
		} catch (IOException e) {
			System.err.println("Ошибка при чтении задачи id=" + id);
			e.printStackTrace();
			return Optional.empty();
		}
	}

	/**
	 * Сохранить задачу в файл.
	 * Если файл существует — он будет перезаписан.
	 *
	 * @param task задача для сохранения
	 */
	public static void saveTask(Task task) {
		File dir = new File(STORAGE_DIR);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		File file = new File(STORAGE_DIR + task.getId() + ".json");

		try {
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, task);
		} catch (IOException e) {
			throw new RuntimeException("Ошибка при сохранении задачи id=" + task.getId(), e);
		}
	}

	/**
	 * Удалить задачу по её идентификатору.
	 *
	 * @param id идентификатор задачи
	 */
	public static void deleteTaskById(long id) {
		try {
			Files.deleteIfExists(Paths.get(STORAGE_DIR + id + ".json"));
		} catch (IOException e) {
			throw new RuntimeException("Ошибка при удалении задачи id=" + id, e);
		}
	}
}
