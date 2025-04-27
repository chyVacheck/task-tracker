
/**
 * @file NotFoundTaskException.java
 * @extends BaseException
 * @author Dmytro Shakh
 */
package com.chyvacheck.tasktracker.core.exceptions.custom;

/**
 * ! java imports
 */
import java.util.Map;

/**
 * ! my imports
 */
import com.chyvacheck.tasktracker.core.base.BaseException;
import com.chyvacheck.tasktracker.core.exceptions.base.ErrorCode;

public class NotFoundTaskException extends BaseException {

	public NotFoundTaskException(String message, Map<String, Object> details) {
		super(message, ErrorCode.TASK_NOT_FOUND, details, null);
	}

}
