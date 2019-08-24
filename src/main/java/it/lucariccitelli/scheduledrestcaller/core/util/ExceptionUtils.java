package it.lucariccitelli.scheduledrestcaller.core.util;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import it.lucariccitelli.scheduledrestcaller.core.dto.ErrorResponse;
import it.lucariccitelli.scheduledrestcaller.core.exception.BaseException;
import it.lucariccitelli.scheduledrestcaller.core.exception.NotFoundException;

/**
 * @author luc created on 15 ago 2019
 *
 */
public class ExceptionUtils {
	private static Logger logger = LoggerFactory.getLogger(ExceptionUtils.class);

	private ExceptionUtils() {
		throw new IllegalStateException("Utils class");
	}

	public static ResponseEntity<ErrorResponse> generateResponse(RuntimeException ex) {
		ErrorResponse response = getErrorResponse(ex);

		return new ResponseEntity<>(response, response.getStatus());
	}

	public static ErrorResponse getErrorResponse(RuntimeException ex) {
		ErrorResponse response = new ErrorResponse();

		if (ex instanceof BaseException)
			response = ((BaseException) ex).getErrorResponse();
		else {
			if (ex instanceof NullPointerException)
				response = parseNullPointerResponse(ex);

			if (ex instanceof EntityNotFoundException)
				response = parseEntityNotFoundResponse(ex);

			logger.error(ex.getClass().getName(), ex);
		}
		return response;
	}

	private static ErrorResponse parseEntityNotFoundResponse(RuntimeException ex) {
		String msg = ex.getMessage();
		String entity = msg.substring(56, msg.indexOf(" with id "));
		Long id = Long.parseLong(msg.substring(msg.indexOf(" with id ") + 9));

		return (new NotFoundException(entity, id)).getErrorResponse();
	}

	private static ErrorResponse parseNullPointerResponse(RuntimeException ex) {
		ErrorResponse response = new ErrorResponse("Oops something went wrong");
		response.addError("nullpointer", ex.getMessage());
		return response;
	}

	/**
	 * Generates a {@link NotFoundException} with a given id
	 * 
	 * @param id
	 * @return a new {@link NotFoundException}
	 */
	public static NotFoundException generateNotFound(String entiy, Long id) {
		return new NotFoundException(entiy.toLowerCase().trim(), id);
	}
}
