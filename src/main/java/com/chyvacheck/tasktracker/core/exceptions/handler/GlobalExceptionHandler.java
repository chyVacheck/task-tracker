
/**
 * @file GlobalExceptionHandler.java
 * 
 * @extends BaseExceptionHandler
 * 
 * @description
 * Глобальный обработчик исключений для всего приложения.
 * Обрабатывает как пользовательские (`BaseException`), так и неожиданные системные ошибки (`Exception`).
 * 
 * @details
 * Задачи:
 * - Обрабатывать все ошибки типа BaseException и возвращать стандартизированный ErrorResponse
 * - Перехватывать неожиданные исключения (RuntimeException, NullPointerException и др.) и предотвращать падение сервера
 * - Выводить отладочную информацию в лог (или консоль)
 * 
 * Использование:
 * - Вызывается единожды в Main классе при старте приложения
 * - Интегрируется через метод register(Javalin app)
 * 
 * Примеры обработки:
 * - 400/404/409 ошибки → от BaseException
 * - 500 ошибка → от всех прочих непойманных исключений
 * 
 * @author
 * Dmytro Shakh
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
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.base.BaseException;
import com.chyvacheck.tasktracker.core.base.BaseExceptionHandler;
import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;
import com.chyvacheck.tasktracker.core.response.http.ErrorResponse;

/**
 * Глобальный обработчик всех ошибок в приложении.
 */
public class GlobalExceptionHandler extends BaseExceptionHandler {

	/**
	 * Регистрирует глобальные обработчики исключений в Javalin-приложении.
	 *
	 * @param app экземпляр Javalin
	 */
	public static void register(Javalin app) {

		// ✅ Обработка всех ошибок BaseException
		app.exception(BaseException.class, (e, ctx) -> {
			ctx.status(e.getStatus());
			ctx.json(new ErrorResponse(
					e.getErrorCode(),
					e.getMessage(),
					e.getErrors(),
					e.getDetails()));
		});

		// ✅ Обработка всех неожиданных ошибок
		app.exception(Exception.class, (e, ctx) -> {
			e.printStackTrace(); // можно заменить на полноценный логгер

			ctx.status(500);
			ctx.json(new ErrorResponse(
					ErrorCode.INTERNAL_ERROR,
					"Something went wrong on the server",
					Map.of("exception", e.getClass().getSimpleName()),
					null));
		});
	}
}