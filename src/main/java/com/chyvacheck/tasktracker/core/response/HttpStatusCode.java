/**
 * @description enum всех статусов ответа
 */

package com.chyvacheck.tasktracker.core.response;

public enum HttpStatusCode {
	// success
	OK(200), // Запрос успешно выполнен
	CREATED(201), // Успешно создан новый ресурс
	ACCEPTED(202), // Запрос принят, но еще не обработан
	NON_AUTHORITATIVE_INFO(203), // Информация получена, но не от первоисточника
	NO_CONTENT(204), // Запрос выполнен, но тело ответа пустое
	RESET_CONTENT(205), // Клиенту нужно сбросить представление (например, форма очистки)
	PARTIAL_CONTENT(206), // Отправлена только часть ресурса (например, для загрузки файлов по частям)
	MULTI_STATUS(207), // Запрос выполнен, но содержит несколько под-ответов
	ALREADY_REPORTED(208), // Элемент уже был перечислен в предыдущих запросах
	IM_USED(226), // Сервер выполнил запрос и применил к ресурсу манипуляций

	// client errors
	BAD_REQUEST(400), // Неверный запрос (например, ошибки в параметрах)
	UNAUTHORIZED(401), // Требуется аутентификация
	PAYMENT_REQUIRED(402), // Требуется оплата (редко используется)
	FORBIDDEN(403), // Доступ запрещен (даже после аутентификации)
	NOT_FOUND(404), // Ресурс не найден
	METHOD_NOT_ALLOWED(405), // HTTP-метод не поддерживается
	NOT_ACCEPTABLE(406), // Клиент не может принять ответ в данном формате
	PROXY_AUTH_REQUIRED(407), // Требуется аутентификация через прокси
	REQUEST_TIMEOUT(408), // Время ожидания запроса истекло
	CONFLICT(409), // Конфликт в запросе (например, попытка создать уже существующий ресурс)
	GONE(410), // Ресурс был, но больше недоступен
	LENGTH_REQUIRED(411), // Требуется указание `Content-Length`
	PRECONDITION_FAILED(412), // Ошибки предварительных условий в заголовках запроса
	PAYLOAD_TOO_LARGE(413), // Размер тела запроса превышает допустимый
	URI_TOO_LONG(414), // Запрос-URI слишком длинный
	UNSUPPORTED_MEDIA_TYPE(415), // Тип контента не поддерживается сервером
	RANGE_NOT_SATISFIABLE(416), // Запрос не может быть произведен в указанном диапазоне
	EXPECTATION_FAILED(417), // Сервер не может выполнить ожидания из заголовка `Expect`
	UNPROCESSABLE_ENTITY(422), // Ошибка валидации данных (чаще в REST API)
	TOO_MANY_REQUESTS(429), // Клиент превысил лимит запросов (rate-limit)

	// server error
	INTERNAL_SERVER_ERROR(500), // Внутренняя ошибка сервера
	NOT_IMPLEMENTED(501), // Метод не поддерживается сервером
	BAD_GATEWAY(502), // Ошибка шлюза (сервер получил некорректный ответ от другого сервера)
	SERVICE_UNAVAILABLE(503), // Сервер перегружен или временно недоступен
	GATEWAY_TIMEOUT(504), // Время ожидания ответа от другого сервера истекло
	HTTP_VERSION_NOT_SUPPORTED(505), // HTTP-версия не поддерживается сервером
	VARIANT_ALSO_NEGOTIATES(506), // Запрос не может быть обработан сервером
	INSUFFICIENT_STORAGE(507), // Недостаточно места на сервере
	LOOP_DETECTED(508); // Циклический редирект

	private final int code;

	HttpStatusCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public boolean isSuccess() {
		return 200 <= this.code && this.code < 400;
	}
}