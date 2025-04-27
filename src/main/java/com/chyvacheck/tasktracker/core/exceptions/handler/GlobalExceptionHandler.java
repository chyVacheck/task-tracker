/**
 * @description Глобальный обработчик ошибок
 */

package com.chyvacheck.tasktracker.core.exceptions.handler;

/**
 * ! lib imports
 */
import io.javalin.Javalin;

/**
 * ! java imports
 */
import java.util.Map;

/**
 * ! own imports
 */
import com.chyvacheck.tasktracker.core.base.BaseException;
import com.chyvacheck.tasktracker.core.response.ErrorResponse;
import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;

public class GlobalExceptionHandler {

	public static void register(Javalin app) {

		// ✅ Обработка всех ошибок ApiException
		app.exception(BaseException.class, (e, ctx) -> {
			ctx.status(e.getStatus());
			ctx.json(new ErrorResponse(
					e.getErrorCode(),
					e.getMessage(),
					e.getErrors(),
					e.getDetails()));
		});

		// ✅ Обработка неожиданных ошибок
		app.exception(Exception.class, (e, ctx) -> {
			e.printStackTrace(); // можно заменить на логгер

			ctx.status(500);
			ctx.json(new ErrorResponse(
					ErrorCode.INTERNAL_ERROR,
					"Something went wrong on the server",
					Map.of("exception", e.getClass().getSimpleName()),
					null));
		});
	}
}