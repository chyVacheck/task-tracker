
/**
 * @file Main.java
 * 
 * @description
 * Главная точка входа в приложение Task Tracker.
 * Настраивает инфраструктуру проекта:
 * - Инициализация ObjectMapper для корректной сериализации данных
 * - Создание экземпляра Javalin сервера
 * - Регистрация контроллеров и маршрутов
 * - Подключение глобального обработчика ошибок
 * 
 * @details
 * - Создаёт и конфигурирует Javalin
 * - Настраивает ObjectMapper
 * - Подключает глобальные обработчики ошибок
 * - Инициализирует все модули (репозиторий, сервис, контроллер, middleware)
 * - Регистрирует все маршруты через RouteManager
 * 
 * Приложение стартует на порту 7070.
 * 
 * При старте выводит в консоль информацию о готовности приложения.
 * 
 * @run
 * java -jar task-tracker.jar
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker;

/**
 * ! lib imports
 */
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ! java imports
 */
import java.util.List;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.exceptions.handler.GlobalExceptionHandler;
import com.chyvacheck.tasktracker.core.routes.RouteManager;
import com.chyvacheck.tasktracker.core.system.ObjectMapperProvider;
import com.chyvacheck.tasktracker.filesystem.SystemSettingsStorage;
import com.chyvacheck.tasktracker.middleware.validate.ValidateMiddleware;
import com.chyvacheck.tasktracker.repository.ITaskRepository;
import com.chyvacheck.tasktracker.repository.impl.FileTaskRepository;
import com.chyvacheck.tasktracker.controller.TaskController;
import com.chyvacheck.tasktracker.service.ITaskService;
import com.chyvacheck.tasktracker.service.TaskService;

/**
 * Главная точка входа в приложение Task Tracker.
 */
public class Main {
	public static void main(String[] args) {
		// Загружаем системные настройки (например, lastId)
		SystemSettingsStorage.loadSettings();

		// Настройка ObjectMapper для работы с датами и запретом автоприведения типов
		ObjectMapper objectMapper = ObjectMapperProvider.get();

		// Инициализация Javalin сервера
		Javalin app = Javalin.create(config -> {
			config.jsonMapper(new JavalinJackson(objectMapper, false));
		}).start(7070);

		// Инициализация middlewares
		ValidateMiddleware.initialize();

		// Создание сервисов и репозиториев
		ITaskRepository taskRepository = FileTaskRepository.initialize();
		ITaskService taskService = TaskService.initialize(taskRepository);
		TaskController taskController = TaskController.initialize(taskService);

		/**
		 * ✅ Регистрация маршрутов через RouteManager
		 */
		RouteManager routeManager = new RouteManager(app);
		routeManager.registerControllers(List.of(
				taskController // в будущем — другие контроллеры
		));

		// Регистрация глобального обработчика ошибок
		new GlobalExceptionHandler(app);

		System.out.println("✅ Task Tracker started on http://localhost:7070/");
	}
}