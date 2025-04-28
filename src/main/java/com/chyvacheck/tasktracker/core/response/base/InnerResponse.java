
/**
 * @file InnerResponse.java
 * 
 * @description
 * Абстрактный базовый класс для всех ответов API.
 * Содержит общие поля и методы для успешных и ошибочных ответов.
 * 
 * @details
 * Основные поля:
 * - status: HTTP-статус ответа
 * - message: текстовое сообщение для клиента
 * - details: дополнительные данные ответа
 * 
 * Используется как базовый класс для SuccessResponse и ErrorResponse.
 * 
 * @example
 * new SuccessResponse(HttpStatusCode.OK, "Task created successfully", Map.of());
 * new ErrorResponse(ErrorCode.USER_NOT_FOUND, "User not found", null, Map.of());
 * 
 * @see SuccessResponse
 * @see ErrorResponse
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.response.base;

/**
 * ! java imports
 */
import java.util.Map;

import com.chyvacheck.tasktracker.core.response.http.HttpStatusCode;

/**
 * Абстрактный базовый класс для всех ответов API.
 */
public abstract class InnerResponse {

	private final HttpStatusCode status;
	private final String message;
	private final Map<String, Object> details;

	/**
	 * Конструктор базового ответа.
	 *
	 * @param status  HTTP-статус
	 * @param message текстовое сообщение
	 * @param details дополнительные детали ответа
	 */
	public InnerResponse(HttpStatusCode status, String message, Map<String, Object> details) {
		this.status = status;
		this.message = message;
		this.details = details;
	}

	/**
	 * Получить числовой код HTTP-статуса.
	 *
	 * @return код статуса
	 */
	public int getStatusCode() {
		return this.status.getCode();
	}

	/**
	 * Получить строковое имя HTTP-статуса.
	 *
	 * @return имя статуса
	 */
	public String getHttpStatusName() {
		return this.status.name();
	}

	/**
	 * Получить текстовое сообщение ответа.
	 *
	 * @return сообщение
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Получить дополнительные детали ответа.
	 *
	 * @return карта дополнительных деталей
	 */
	public Map<String, Object> getDetails() {
		return this.details;
	}
}