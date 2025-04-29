
/**
 * @file RouteDefinition.java
 * 
 * @description
 * Класс описания маршрута приложения (HTTP-метод, путь, обработчик).
 * Используется для декларативного определения всех маршрутов в контроллерах.
 * 
 * @details
 * Содержит следующую информацию о маршруте:
 * - HTTP-метод запроса (GET, POST, PATCH, PUT, DELETE)
 * - Путь маршрута (например, "/tasks/{id}")
 * - Обработчик запроса (функция, выполняющая логику)
 * 
 * @example
 * new RouteDefinition("GET", "/tasks", this::getAllTasks)
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.routes;

/**
 * ! lib imports
 */
import io.javalin.http.Handler;

/**
 * Класс для описания маршрута HTTP-запроса.
 */
public class RouteDefinition {

	private final String method; // todo переписать на enum
	private final String path;
	private final Handler handler;

	/**
	 * Конструктор маршрута.
	 *
	 * @param method  HTTP-метод запроса
	 * @param path    путь запроса
	 * @param handler обработчик запроса
	 */
	public RouteDefinition(String method, String path, Handler handler) {
		this.method = method;
		this.path = path;
		this.handler = handler;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public Handler getHandler() {
		return handler;
	}
}