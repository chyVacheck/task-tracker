/**
 * @file ObjectMapperProvider.java
 * 
 * @description
 * Предоставляет настроенный экземпляр ObjectMapper для всего проекта.
 * Используется для сериализации и десериализации JSON как в HTTP, так и в файловой системе.
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.system;

/**
 * ! lib imports
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;

public class ObjectMapperProvider {

	private static final ObjectMapper objectMapper = createConfiguredObjectMapper();

	private static ObjectMapper createConfiguredObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.coercionConfigDefaults()
				.setCoercion(CoercionInputShape.Boolean, CoercionAction.Fail)
				.setCoercion(CoercionInputShape.Integer, CoercionAction.Fail)
				.setCoercion(CoercionInputShape.Float, CoercionAction.Fail);
		return mapper;
	}

	/**
	 * Получить глобально сконфигурированный ObjectMapper.
	 *
	 * @return экземпляр ObjectMapper
	 */
	public static ObjectMapper get() {
		return objectMapper;
	}
}