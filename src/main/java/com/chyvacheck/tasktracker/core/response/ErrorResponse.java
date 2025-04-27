
/**
 * @file ErrorResponse.java
 * 
 * @extends InnerResponse
 * 
 * @description
 * Реализация ответа API в случае ошибки.
 * Представляет результат запроса с ошибочным исходом (статус 4xx или 5xx).
 * 
 * @details
 * Содержит:
 * - errorCode: тип ошибки (ErrorCode)
 * - errors: конкретные ошибки по полям
 * - details: дополнительные детали ошибки
 * 
 * Проверяет, что переданный HTTP-статус является кодом ошибки.
 * 
 * Пример использования:
 * return new ErrorResponse(ErrorCode.TASK_NOT_FOUND, "Task not found", null, Map.of("id", 1L));
 * 
 * @see InnerResponse
 * @see ErrorCode
 * 
 * @throws IllegalArgumentException если передан статус успеха вместо статуса ошибки
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.response;

/**
 * ! java imports
 */
import java.util.Map;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;

/**
 * Реализация ответа API в случае ошибки.
 */
public class ErrorResponse extends InnerResponse {

	private final ErrorCode errorCode;

	private final Map<String, String> errors;

	/**
	 * Конструктор ответа с ошибкой.
	 *
	 * @param errorCode код ошибки
	 * @param message   сообщение об ошибке
	 * @param errors    ошибки по полям (если есть)
	 * @param details   дополнительные детали ошибки
	 */
	public ErrorResponse(
			ErrorCode errorCode,
			String message,
			Map<String, String> errors,
			Map<String, Object> details) {
		super(errorCode.getHttpStatus(), message, details);

		if (errorCode.getHttpStatus().isSuccess()) {
			throw new IllegalArgumentException(
					"ErrorResponse can only use error HTTP status codes (4xx or 5xx)!");
		}

		this.errorCode = errorCode;
		this.errors = errors;
	}

	/**
	 * Получить код ошибки.
	 *
	 * @return ErrorCode
	 */
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Получить ошибки по полям.
	 *
	 * @return карта ошибок по полям
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

}