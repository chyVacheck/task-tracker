
/**
 * @file ErrorCode.java
 * 
 * @description
 * Перечисление всех кодов ошибок, используемых в приложении.
 * Каждый код ошибки связан с соответствующим HTTP-статусом для правильной генерации ответов API.
 * 
 * @details
 * Ошибки сгруппированы по категориям HTTP-статусов:
 * - 400 (Bad Request): Ошибки запроса клиента
 * - 401 (Unauthorized): Ошибки аутентификации
 * - 403 (Forbidden): Ошибки авторизации
 * - 404 (Not Found): Ошибки отсутствия ресурса
 * - 409 (Conflict): Конфликтные ситуации при запросах
 * - 413 (Payload Too Large): Превышение допустимого размера запроса
 * - 422 (Unprocessable Entity): Ошибки бизнес-логики
 * - 500 (Internal Server Error): Ошибки сервера
 * 
 * Используется в BaseException и для формирования ErrorResponse в API.
 * 
 * @example
 * throw new BaseException(\"Task not found\", ErrorCode.TASK_NOT_FOUND, Map.of(\"id\", 1L), null);
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.exceptions.base;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.response.HttpStatusCode;

/**
 * Перечисление всех кодов ошибок в приложении.
 * Каждый ErrorCode связан с определённым HTTP-статусом.
 */
public enum ErrorCode {
	// --- 400 BAD REQUEST ---

	/**
	 * Переданы неизвестные свойства в запросе (например, лишние поля в JSON).
	 */
	UNKNOWN_PROPERTY(HttpStatusCode.BAD_REQUEST),

	/**
	 * Ошибка валидации входных данных (например, некорректное значение в поле).
	 */
	VALIDATION_FAILED(HttpStatusCode.BAD_REQUEST),

	/**
	 * Ошибка парсинга JSON-запроса (например, синтаксическая ошибка).
	 */
	JSON_PARSE_ERROR(HttpStatusCode.BAD_REQUEST),

	/**
	 * Ошибка преобразования JSON в DTO (например, неправильный тип данных).
	 */
	JSON_MAPPING_ERROR(HttpStatusCode.BAD_REQUEST),

	/**
	 * Некорректные параметры запроса (например, неправильные query-параметры).
	 */
	INVALID_QUERY_PARAMETERS(HttpStatusCode.BAD_REQUEST),

	// --- 401 UNAUTHORIZED ---

	/**
	 * Необходима аутентификация пользователя.
	 */
	UNAUTHORIZED(HttpStatusCode.UNAUTHORIZED),

	/**
	 * Срок действия токена истёк.
	 */
	TOKEN_EXPIRED(HttpStatusCode.UNAUTHORIZED),

	/**
	 * Токен недействителен или подделан.
	 */
	TOKEN_INVALID(HttpStatusCode.UNAUTHORIZED),

	// --- 403 FORBIDDEN ---

	/**
	 * У пользователя нет доступа к ресурсу, даже после аутентификации.
	 */
	ACCESS_DENIED(HttpStatusCode.FORBIDDEN),

	/**
	 * Требуется определённая роль для доступа к ресурсу.
	 */
	ROLE_REQUIRED(HttpStatusCode.FORBIDDEN),

	// --- 404 NOT FOUND ---

	/**
	 * Пользователь с указанными данными не найден.
	 */
	USER_NOT_FOUND(HttpStatusCode.NOT_FOUND),

	/**
	 * Задача с указанным ID не найдена.
	 */
	TASK_NOT_FOUND(HttpStatusCode.NOT_FOUND),

	/**
	 * Ресурс с указанным идентификатором не найден.
	 */
	RESOURCE_NOT_FOUND(HttpStatusCode.NOT_FOUND),

	// --- 409 CONFLICT ---

	/**
	 * Пользователь с такими данными уже существует.
	 */
	USER_ALREADY_EXISTS(HttpStatusCode.CONFLICT),

	/**
	 * Email уже занят другим пользователем.
	 */
	DUPLICATE_EMAIL(HttpStatusCode.CONFLICT),

	/**
	 * Имя пользователя уже занято.
	 */
	USERNAME_ALREADY_TAKEN(HttpStatusCode.CONFLICT),

	// --- 413 PAYLOAD TOO LARGE ---

	/**
	 * Размер запроса превышает допустимые ограничения.
	 */
	PAYLOAD_TOO_LARGE(HttpStatusCode.PAYLOAD_TOO_LARGE),

	// --- 422 UNPROCESSABLE ENTITY ---

	/**
	 * Нарушение бизнес-правил (например, недопустимое состояние объекта).
	 */
	BUSINESS_RULE_VIOLATION(HttpStatusCode.UNPROCESSABLE_ENTITY),

	/**
	 * Внутренняя ошибка сервера.
	 */
	INTERNAL_ERROR(HttpStatusCode.INTERNAL_SERVER_ERROR);

	private final HttpStatusCode status;

	ErrorCode(HttpStatusCode status) {
		this.status = status;
	}

	/**
	 * Получить связанный HTTP-статус для данного кода ошибки.
	 *
	 * @return HTTP-статус
	 */
	public HttpStatusCode getHttpStatus() {
		return status;
	}
}