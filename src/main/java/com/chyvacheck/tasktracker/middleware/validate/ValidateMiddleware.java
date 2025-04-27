
/**
 * @file ValidateMiddleware.java
 * 
 * @description
 * Middleware для валидации входящих данных в API.
 * Обеспечивает централизованную проверку тела запроса (body), query-параметров и path-параметров.
 * 
 * @details
 * Основные задачи:
 * - Десериализация входящих данных в DTO
 * - Проверка валидности DTO через Jakarta Bean Validation
 * - Обработка ошибок десериализации и генерация стандартизированных исключений (BaseException)
 * 
 * Источники данных:
 * - fromBody(Context ctx, Class<T> dtoClass) — валидация тела запроса (JSON)
 * - fromQuery(Context ctx, Class<T> dtoClass) — валидация query-параметров
 * - fromPath(Context ctx, Class<T> dtoClass) — валидация path-параметров
 * 
 * Все валидационные ошибки автоматически конвертируются в формат ErrorResponse.
 * 
 * Пример использования в контроллере:
 * TaskCreateDto dto = ValidateMiddleware.fromBody(ctx, TaskCreateDto.class);
 * 
 * @author
 * Dmytro Shakh
 */
package com.chyvacheck.tasktracker.middleware.validate;

/**
 * ! lib imports
 */
import io.javalin.http.Context;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * ! java imports
 */
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.base.BaseException;
import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

/**
 * Middleware для централизованной валидации входящих данных.
 */
public class ValidateMiddleware {

	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = factory.getValidator();

	/**
	 * Валидировать тело запроса (body) и преобразовать в DTO.
	 *
	 * @param ctx      контекст запроса
	 * @param dtoClass класс целевого DTO
	 * @return валидный экземпляр DTO
	 * @throws Exception если валидация или десериализация не удалась
	 */
	public static <T> T fromBody(Context ctx, Class<T> dtoClass) throws Exception {
		try {
			T dto = ctx.bodyAsClass(dtoClass);
			return validate(dto);
		} catch (Exception e) {
			handleDeserializationError(e);
			throw new IllegalStateException("Unreachable");
		}
	}

	/**
	 * Валидировать query-параметры и преобразовать в DTO.
	 *
	 * @param ctx      контекст запроса
	 * @param dtoClass класс целевого DTO
	 * @return валидный экземпляр DTO
	 * @throws Exception если валидация или десериализация не удалась
	 */
	public static <T> T fromQuery(Context ctx, Class<T> dtoClass) throws Exception {
		try {
			T dto = dtoClass.getDeclaredConstructor().newInstance();

			for (Field field : dtoClass.getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				String value = ctx.queryParam(name);
				if (value != null) {
					// преобразовать string → нужный тип
					Object parsed = parseValue(field.getType(), value);
					field.set(dto, parsed);
				}
			}

			return validate(dto);
		} catch (Exception e) {
			handleDeserializationError(e);
			throw new IllegalStateException("Unreachable");
		}
	}

	/**
	 * Валидировать path-параметры и преобразовать в DTO.
	 *
	 * @param ctx      контекст запроса
	 * @param dtoClass класс целевого DTO
	 * @return валидный экземпляр DTO
	 * @throws Exception если валидация или десериализация не удалась
	 */
	public static <T> T fromPath(Context ctx, Class<T> dtoClass) throws Exception {
		try {
			T dto = dtoClass.getDeclaredConstructor().newInstance();

			for (Field field : dtoClass.getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				String value = ctx.pathParamMap().get(name);

				if (value != null) {
					Object parsed = parseValue(field.getType(), value);
					field.set(dto, parsed);
				}
			}

			return validate(dto);
		} catch (Exception e) {
			handleDeserializationError(e);
			throw new IllegalStateException("Unreachable");
		}
	}

	/**
	 * Преобразовать строковое значение в нужный тип поля.
	 *
	 * @param type  класс типа поля
	 * @param value строковое значение
	 * @return преобразованное значение
	 */
	private static Object parseValue(Class<?> type, String value) {
		if (type == int.class || type == Integer.class)
			return Integer.parseInt(value);
		if (type == long.class || type == Long.class)
			return Long.parseLong(value);
		if (type == boolean.class || type == Boolean.class)
			return Boolean.parseBoolean(value);
		if (type == String.class)
			return value;
		// И т.д. по необходимости
		return null;
	}

	/**
	 * Провести валидацию DTO через Validator.
	 *
	 * @param dto объект DTO
	 * @param <T> тип DTO
	 * @return валидный объект DTO
	 * @throws BaseException если обнаружены ошибки валидации
	 */
	private static <T> T validate(T dto) {
		System.out.println("📥 validate вызван");

		Set<ConstraintViolation<T>> violations = validator.validate(dto);

		if (!violations.isEmpty()) {
			Map<String, String> errors = new HashMap<>();
			for (ConstraintViolation<T> violation : violations) {
				String field = violation.getPropertyPath().toString();
				String message = violation.getMessage();
				errors.put(field, message);
			}

			throw new BaseException("Validation failed", ErrorCode.VALIDATION_FAILED, null, errors);

		}

		return dto;
	}

	/**
	 * Обработать ошибки десериализации запроса.
	 *
	 * @param e исключение
	 * @throws BaseException конвертированная ошибка
	 */
	private static void handleDeserializationError(Exception e) throws Exception {
		// ? Если проверка на целостность `json` объекта не прошла
		if (e instanceof JsonParseException jpe) {
			throw new BaseException(
					"Invalid JSON format",
					ErrorCode.JSON_PARSE_ERROR,
					Map.of("exception", jpe.getClass().getSimpleName()),
					null);
		}

		// ? Если ключи не совпадают
		if (e instanceof UnrecognizedPropertyException upe) {
			String unexpectedField = upe.getPropertyName();

			Map<String, String> errors = Map.of(
					unexpectedField, "Unexpected field");

			throw new BaseException(
					"Request contains unexpected property",
					ErrorCode.UNKNOWN_PROPERTY,
					Map.of("exception", upe.getClass().getSimpleName()),
					errors);
		}

		// ? Если значение не совпадают
		if (e instanceof MismatchedInputException mie) {

			// Получаем имя поля (если есть)
			String field = mie.getPath().stream()
					.map(ref -> ref.getFieldName())
					.filter(name -> name != null)
					.findFirst()
					.orElse("body");

			String expected = mie.getTargetType() != null
					? mie.getTargetType().getSimpleName()
					: "unknown";

			String received = "unknown";

			// Если это InvalidFormatException — извлечь полученное значение
			if (mie instanceof InvalidFormatException ife) {
				Object value = ife.getValue();
				if (value != null) {
					received = value.getClass().getSimpleName().toLowerCase();
				} else {
					received = "null";
				}
			}

			// Формируем финальное сообщение
			String reason = "Expected " + expected + " but got " + received;

			Map<String, String> fieldErrors = Map.of(field, reason);

			throw new BaseException(
					"Invalid request body",
					ErrorCode.VALIDATION_FAILED,
					Map.of("exception", mie.getClass().getSimpleName()),
					fieldErrors);
		}

		// ? Если проверка на целостность `json` объекта не прошла
		if (e instanceof JsonMappingException jme) {
			throw new BaseException(
					"Invalid JSON format",
					ErrorCode.JSON_MAPPING_ERROR,
					Map.of("exception", jme.getClass().getSimpleName()),
					null);
		}

		// ? Пробрасываем дальше все остальные исключения
		throw e;
	}
}
