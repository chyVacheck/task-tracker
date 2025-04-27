/**
 * @description Реализация абстрактного класса ответа, ответ с ошибкой
 */

package com.chyvacheck.tasktracker.core.response;

/**
 * ! java imports
 */
import java.util.Map;

import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;

public class ErrorResponse extends InnerResponse {

	private final ErrorCode errorCode;

	private final Map<String, String> errors;

	public ErrorResponse(
			ErrorCode errorCode,
			String message,
			Map<String, String> errors,
			Map<String, Object> details) {
		super(errorCode.getHttpStatus(), message, details);

		if (errorCode.getHttpStatus().isSuccess()) {
			throw new IllegalArgumentException(
					"ErrorResponse can only be HttpStatusCode witch are error status codes!");
		}

		this.errorCode = errorCode;
		this.errors = errors;
	}

	public ErrorCode getErrorCode() {
		return this.errorCode;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}