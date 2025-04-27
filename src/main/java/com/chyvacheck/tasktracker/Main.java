
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
 * Пример маршрутов:
 * - GET /tasks
 * - POST /tasks
 * - PATCH /tasks/{id}
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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.exceptions.handler.GlobalExceptionHandler;
import com.chyvacheck.tasktracker.middleware.validate.ValidateMiddleware;
import com.chyvacheck.tasktracker.repository.ITaskRepository;
import com.chyvacheck.tasktracker.repository.impl.InMemoryTaskRepository;
import com.chyvacheck.tasktracker.controller.TaskController;
import com.chyvacheck.tasktracker.service.ITaskService;
import com.chyvacheck.tasktracker.service.TaskService;

/**
 * Главная точка входа в приложение Task Tracker.
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("🧠 Загружен класс: " + ValidateMiddleware.class.getProtectionDomain().getCodeSource());

		// Настройка ObjectMapper для работы с датами и запретом автоприведения типов
		ObjectMapper objectMapper = new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// для коректно сериализации LocalDateTime в iso формат
		objectMapper
				.coercionConfigDefaults()
				.setCoercion(CoercionInputShape.Boolean, CoercionAction.Fail)
				.setCoercion(CoercionInputShape.Integer, CoercionAction.Fail)
				.setCoercion(CoercionInputShape.Float, CoercionAction.Fail);

		// Инициализация Javalin сервера
		Javalin app = Javalin.create(config -> {
			config.jsonMapper(new JavalinJackson(objectMapper, false));
		}).start(7070);

		// Создание сервисов и репозиториев
		ITaskRepository taskRepository = new InMemoryTaskRepository();
		ITaskService taskService = new TaskService(taskRepository);
		TaskController taskController = new TaskController(taskService);

		// Регистрация маршрутов
		taskController.registerRoutes(app);

		// Регистрация глобального обработчика ошибок
		GlobalExceptionHandler.register(app);

		System.out.println("🚀 Start!");
	}
}