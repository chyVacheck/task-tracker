
/**
 * @file HttpStatusCode.java
 * 
 * @description
 * Перечисление всех основных HTTP-статусов, используемых для формирования ответов API.
 * 
 * @details
 * Группы статусов:
 * - 2xx — Успешные ответы (Success)
 * - 4xx — Ошибки клиента (Client Error)
 * - 5xx — Ошибки сервера (Server Error)
 * 
 * Каждый статус включает в себя числовой код, который может быть использован для построения SuccessResponse или ErrorResponse.
 * 
 * Примеры использования:
 * - HttpStatusCode.OK (200)
 * - HttpStatusCode.NOT_FOUND (404)
 * - HttpStatusCode.INTERNAL_SERVER_ERROR (500)
 * 
 * Методы:
 * - getCode(): получить числовой код статуса
 * - isSuccess(): проверить, является ли статус успешным (2xx–3xx)
 * 
 * @example
 * HttpStatusCode code = HttpStatusCode.CREATED;
 * if (code.isSuccess()) { ... }
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.response;

public enum HttpStatusCode {

	// --- Success (2xx) ---

	OK(200), // Запрос успешно выполнен
	CREATED(201), // Успешно создан новый ресурс
	ACCEPTED(202), // Запрос принят, но еще не обработан
	NON_AUTHORITATIVE_INFO(203), // Информация получена, но не от первоисточника
	NO_CONTENT(204), // Запрос выполнен, но тело ответа пустое
	RESET_CONTENT(205), // Клиенту нужно сбросить представление (например, очистить форму)
	PARTIAL_CONTENT(206), // Отправлена только часть ресурса
	MULTI_STATUS(207), // Запрос выполнен, но содержит несколько под-ответов
	ALREADY_REPORTED(208), // Элемент уже был перечислен в предыдущем ответе
	IM_USED(226), // Сервер применил к ресурсу манипуляции

	// --- Client Error (4xx) ---

	BAD_REQUEST(400), // Неверный запрос
	UNAUTHORIZED(401), // Требуется аутентификация
	PAYMENT_REQUIRED(402), // Требуется оплата (редко используется)
	FORBIDDEN(403), // Доступ запрещен
	NOT_FOUND(404), // Ресурс не найден
	METHOD_NOT_ALLOWED(405), // HTTP-метод не поддерживается
	NOT_ACCEPTABLE(406), // Клиент не может принять ответ в данном формате
	PROXY_AUTH_REQUIRED(407), // Требуется аутентификация через прокси
	REQUEST_TIMEOUT(408), // Время ожидания запроса истекло
	CONFLICT(409), // Конфликт в запросе
	GONE(410), // Ресурс больше недоступен
	LENGTH_REQUIRED(411), // Требуется заголовок Content-Length
	PRECONDITION_FAILED(412), // Ошибки предварительных условий
	PAYLOAD_TOO_LARGE(413), // Тело запроса слишком большое
	URI_TOO_LONG(414), // URI слишком длинный
	UNSUPPORTED_MEDIA_TYPE(415), // Тип содержимого не поддерживается
	RANGE_NOT_SATISFIABLE(416), // Указанный диапазон недопустим
	EXPECTATION_FAILED(417), // Ошибка выполнения заголовка Expect
	UNPROCESSABLE_ENTITY(422), // Ошибка валидации данных
	TOO_MANY_REQUESTS(429), // Слишком много запросов (rate-limit)

	// --- Server Error (5xx) ---

	INTERNAL_SERVER_ERROR(500), // Внутренняя ошибка сервера
	NOT_IMPLEMENTED(501), // Метод не поддерживается сервером
	BAD_GATEWAY(502), // Плохой ответ от другого сервера
	SERVICE_UNAVAILABLE(503), // Сервер временно недоступен
	GATEWAY_TIMEOUT(504), // Таймаут при ожидании ответа
	HTTP_VERSION_NOT_SUPPORTED(505), // HTTP-версия не поддерживается
	VARIANT_ALSO_NEGOTIATES(506), // Ошибка согласования
	INSUFFICIENT_STORAGE(507), // Недостаточно места на сервере
	LOOP_DETECTED(508); // Обнаружено зацикливание

	private final int code;

	/**
	 * Конструктор перечисления.
	 *
	 * @param code числовой код HTTP-статуса
	 */
	HttpStatusCode(int code) {
		this.code = code;
	}

	/**
	 * Получить числовой код HTTP-статуса.
	 *
	 * @return числовой код
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * Проверить, является ли статус успешным (2xx–3xx).
	 *
	 * @return true если успешный, иначе false
	 */
	public boolean isSuccess() {
		return 200 <= this.code && this.code < 400;
	}
}