/**
 * @description Реализация абстрактного класса ответа, успешный ответ
 */

package com.chyvacheck.tasktracker.core.response;

/**
 * ! java imports
 */
import java.util.Map;

public class SuccessResponse extends InnerResponse {

	public SuccessResponse(HttpStatusCode status, String message, Map<String, Object> details) {
		super(status, message, details);

		if (!status.isSuccess()) {
			throw new IllegalArgumentException(
					"SuccessResponse can only be HttpStatusCode witch are success status codes!");
		}

	}

}