/**
 * @description enum всех кодов ошибок
 */

package com.chyvacheck.tasktracker.core.exceptions.base;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.response.HttpStatusCode;

public enum ErrorCode {
	// 400 — BAD REQUEST
	UNKNOWN_PROPERTY(HttpStatusCode.BAD_REQUEST),
	VALIDATION_FAILED(HttpStatusCode.BAD_REQUEST),
	JSON_PARSE_ERROR(HttpStatusCode.BAD_REQUEST),
	JSON_MAPPING_ERROR(HttpStatusCode.BAD_REQUEST),
	INVALID_QUERY_PARAMETERS(HttpStatusCode.BAD_REQUEST),

	// 401 — UNAUTHORIZED
	UNAUTHORIZED(HttpStatusCode.UNAUTHORIZED),
	TOKEN_EXPIRED(HttpStatusCode.UNAUTHORIZED),
	TOKEN_INVALID(HttpStatusCode.UNAUTHORIZED),

	// 403 — FORBIDDEN
	ACCESS_DENIED(HttpStatusCode.FORBIDDEN),
	ROLE_REQUIRED(HttpStatusCode.FORBIDDEN),

	// 404 — NOT FOUND
	USER_NOT_FOUND(HttpStatusCode.NOT_FOUND),
	TASK_NOT_FOUND(HttpStatusCode.NOT_FOUND),
	RESOURCE_NOT_FOUND(HttpStatusCode.NOT_FOUND),

	// 409 — CONFLICT
	USER_ALREADY_EXISTS(HttpStatusCode.CONFLICT),
	DUPLICATE_EMAIL(HttpStatusCode.CONFLICT),
	USERNAME_ALREADY_TAKEN(HttpStatusCode.CONFLICT),

	// 413 — PAYLOAD TOO LARGE
	PAYLOAD_TOO_LARGE(HttpStatusCode.PAYLOAD_TOO_LARGE),

	// 422 — UNPROCESSABLE ENTITY
	BUSINESS_RULE_VIOLATION(HttpStatusCode.UNPROCESSABLE_ENTITY),

	// 500 — INTERNAL SERVER ERROR
	INTERNAL_ERROR(HttpStatusCode.INTERNAL_SERVER_ERROR);

	private final HttpStatusCode status;

	ErrorCode(HttpStatusCode status) {
		this.status = status;
	}

	public HttpStatusCode getHttpStatus() {
		return status;
	}
}