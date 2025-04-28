
/**
 * @file SuccessResponse.java
 * 
 * @extends InnerResponse
 * 
 * @description
 * Реализация успешного ответа API.
 * Представляет результат выполнения запроса с положительным исходом (статус 2xx).
 * 
 * @details
 * Проверяет, что переданный статус действительно является успешным.
 * 
 * Пример использования:
 * return new SuccessResponse(HttpStatusCode.CREATED, "Task successfully created", Map.of("taskId", 123));
 * 
 * @see InnerResponse
 * @see HttpStatusCode
 * 
 * @throws IllegalArgumentException если передан код ошибки вместо кода успеха
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
 * Реализация успешного ответа API.
 */
public class SuccessResponse extends InnerResponse {

	private final Object data;

	/**
	 * Конструктор успешного ответа.
	 *
	 * @param status  HTTP-статус (только успешные коды 2xx)
	 * @param message сообщение для клиента
	 * @param details дополнительные детали ответа
	 */
	public SuccessResponse(HttpStatusCode status, String message, Object data, Map<String, Object> details) {
		super(status, message, details);

		if (!status.isSuccess()) {
			throw new IllegalArgumentException(
					"SuccessResponse can only use success HTTP status codes (2xx)!");
		}

		this.data = data;

	}

	/**
	 * Получить данные ответа.
	 *
	 * @return объект данных
	 */
	public Object getData() {
		return data;
	}
}