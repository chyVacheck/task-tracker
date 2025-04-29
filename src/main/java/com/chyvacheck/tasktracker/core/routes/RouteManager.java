
/**
 * @file RouteManager.java
 * 
 * @description
 * Класс для централизованной регистрации всех маршрутов приложения.
 * 
 * @details
 * Принимает список контроллеров, реализующих {@link Routable}, и регистрирует их маршруты в экземпляре Javalin.
 * 
 * @example
 * routeManager.registerControllers(List.of(taskController));
 * 
 * @see Routable
 * @see RouteDefinition
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.routes;

/**
 * ! lib imports
 */
import io.javalin.Javalin;

/**
 * ! java imports
 */
import java.util.List;

/**
 * Менеджер маршрутов приложения.
 */
public class RouteManager {

	private final Javalin app;

	/**
	 * Конструктор RouteManager.
	 *
	 * @param app экземпляр Javalin
	 */
	public RouteManager(Javalin app) {
		this.app = app;
	}

	/**
	 * Регистрирует маршруты всех переданных контроллеров.
	 *
	 * @param controllers список контроллеров, реализующих Routable
	 */
	public void registerControllers(List<Routable> controllers) {
		for (Routable controller : controllers) {
			for (RouteDefinition route : controller.routes()) {
				switch (route.getMethod()) {
					case "GET" -> app.get(route.getPath(), route.getHandler());
					case "POST" -> app.post(route.getPath(), route.getHandler());
					case "PATCH" -> app.patch(route.getPath(), route.getHandler());
					case "PUT" -> app.put(route.getPath(), route.getHandler());
					case "DELETE" -> app.delete(route.getPath(), route.getHandler());
					default -> throw new IllegalArgumentException("Unsupported HTTP method: " + route.getMethod());
				}
			}
		}
	}
}