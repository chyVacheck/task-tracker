/**
 * @description Абстрактный класс ответа
 */

package com.chyvacheck.tasktracker.core.response;

/**
 * ! java imports
 */
import java.util.Map;

public abstract class InnerResponse {
	private final HttpStatusCode status;
	private final String message;
	private final Map<String, Object> details;

	public InnerResponse(HttpStatusCode status, String message, Map<String, Object> details) {
		this.status = status;
		this.message = message;
		this.details = details;
	}

	/**
	 * Gets the int code value for status
	 * 
	 * @return status code
	 */
	public int getStatusCode() {
		return this.status.getCode();
	}

	/**
	 * Gets the string value for status
	 * 
	 * @return status
	 */
	public String getHttpStatusName() {
		return this.status.name();
	}

	/**
	 * Gets the string value for message
	 * 
	 * @return message
	 */
	public String getMessage() {
		return this.message;
	}

	public Map<String, Object> getDetails() {
		return this.details;
	}
}