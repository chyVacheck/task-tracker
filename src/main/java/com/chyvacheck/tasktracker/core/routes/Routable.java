
/**
 * @file Routable.java
 * 
 * @description
 * Интерфейс для контроллеров, которые предоставляют список маршрутов для регистрации.
 * 
 * @details
 * Все контроллеры должны реализовать этот интерфейс и возвращать список определённых маршрутов.
 * 
 * @example
 * List<RouteDefinition> routes = taskController.routes();
 * 
 * @see RouteDefinition
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.routes;

/**
 * ! java imports
 */
import java.util.List;

/**
 * Интерфейс для контроллеров, которые предоставляют маршруты.
 */
public interface Routable {
	/**
	 * Получить список всех маршрутов контроллера.
	 *
	 * @return список маршрутов
	 */
	List<RouteDefinition> routes();
}