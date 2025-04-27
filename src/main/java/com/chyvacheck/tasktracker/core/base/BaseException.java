
/**
 * @file BaseException.java
 * @author Dmytro Shakh
 */
package com.chyvacheck.tasktracker.core.base;

/**
 * ! java imports
 */
import java.util.Map;

import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;

/**
 * Abstract base class for custom application exceptions.
 */
public class BaseException extends RuntimeException {

	private final ErrorCode code;
	private final Map<String, Object> details;
	private final Map<String, String> errors;

	public BaseException(String message, ErrorCode code, Map<String, Object> details,
			Map<String, String> errors) {
		super(message);

		this.code = code;
		this.details = details;
		this.errors = errors;
	}

	public ErrorCode getErrorCode() {
		return this.code;
	}

	public int getStatus() {
		return code.getHttpStatus().getCode();
	}

	public String getErrorCodeName() {
		return code.name();
	}

	public String getMessage() {
		return super.getMessage();
	}

	public Map<String, Object> getDetails() {
		return this.details;
	}

	public Map<String, String> getErrors() {
		return this.errors;
	}
}