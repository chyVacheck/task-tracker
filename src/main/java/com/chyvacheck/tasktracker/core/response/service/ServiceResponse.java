
/**
 * @file ServiceResponse.java
 * 
 * @description
 * Обобщённый ответ сервиса, описывающий результат выполнения бизнес-операции.
 * Содержит тип выполненного процесса и возвращаемые данные.
 * 
 * @param <T> Тип данных, возвращаемых в ответе
 * 
 * @see ServiceProcessType
 * @see SuccessResponse
 * 
 * @author
 * Dmytro Shakh
 */

package com.chyvacheck.tasktracker.core.response.service;

/**
 * ! my imports
 */

/**
 * Обобщённый ответ сервиса.
 *
 * @param <T> тип данных, связанных с выполненным процессом
 */
public class ServiceResponse<T> {
	private final ServiceProcessType process;
	private final T data;

	/**
	 * Конструктор ответа сервиса.
	 *
	 * @param process тип выполненного процесса
	 * @param data    данные результата выполнения операции
	 */
	public ServiceResponse(ServiceProcessType process, T data) {
		this.process = process;
		this.data = data;
	}

	/**
	 * Получить тип выполненного процесса.
	 *
	 * @return тип процесса
	 */
	public ServiceProcessType getProcess() {
		return process;
	}

	/**
	 * Получить данные результата выполнения.
	 *
	 * @return данные
	 */
	public T getData() {
		return data;
	}
}
