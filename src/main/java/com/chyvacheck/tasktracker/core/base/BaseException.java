
/**
 * @file BaseException.java
 * 
 * @extends RuntimeException
 * 
 * @description
 * Базовый класс всех пользовательских (кастомных) исключений в приложении.
 * Расширяет RuntimeException и добавляет дополнительные поля для более гибкой обработки ошибок в API.
 * 
 * @details
 * Дополнительные поля:
 * - code: код ошибки (`ErrorCode`), отражающий тип ошибки
 * - details: дополнительные детали ошибки (например, параметры запроса)
 * - errors: конкретные ошибки валидации по полям
 * 
 * Используется как родительский класс для всех специальных исключений (например, NotFoundTaskException, ValidationFailedException и др.).
 * 
 * При генерации ответа API на ошибку формирует стандартную структуру ErrorResponse.
 * 
 * Пример использования:
 * throw new BaseException(\"User not found\", ErrorCode.USER_NOT_FOUND, Map.of(\"id\", 1L), null);
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.base;

/**
 * ! java imports
 */
import java.util.Map;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;

/**
 * Абстрактный базовый класс для всех пользовательских исключений.
 */
public class BaseException extends RuntimeException {

	private final ErrorCode code;
	private final Map<String, Object> details;
	private final Map<String, String> errors;

	/**
	 * Конструктор базового исключения.
	 *
	 * @param message текст ошибки для пользователя
	 * @param code    код ошибки (ErrorCode)
	 * @param details дополнительные детали ошибки (параметры запроса)
	 * @param errors  ошибки валидации по полям
	 */
	public BaseException(String message, ErrorCode code, Map<String, Object> details,
			Map<String, String> errors) {
		super(message);

		this.code = code;
		this.details = details;
		this.errors = errors;
	}

	/**
	 * Получить код ошибки.
	 *
	 * @return код ошибки (ErrorCode)
	 */
	public ErrorCode getErrorCode() {
		return this.code;
	}

	/**
	 * Получить HTTP-статус ошибки.
	 *
	 * @return числовой код HTTP-статуса (например, 400, 404, 500)
	 */
	public int getStatus() {
		return code.getHttpStatus().getCode();
	}

	/**
	 * Получить название кода ошибки.
	 *
	 * @return строковое имя кода ошибки
	 */
	public String getErrorCodeName() {
		return code.name();
	}

	/**
	 * Получить сообщение ошибки.
	 *
	 * @return текст сообщения
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}

	/**
	 * Получить дополнительные детали ошибки.
	 *
	 * @return карта деталей
	 */
	public Map<String, Object> getDetails() {
		return this.details;
	}

	/**
	 * Получить ошибки валидации по полям.
	 *
	 * @return карта ошибок валидации
	 */
	public Map<String, String> getErrors() {
		return this.errors;
	}
}