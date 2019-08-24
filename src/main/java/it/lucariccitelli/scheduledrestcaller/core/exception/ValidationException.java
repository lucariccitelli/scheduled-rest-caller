package it.lucariccitelli.scheduledrestcaller.core.exception;

import org.springframework.http.HttpStatus;

import it.lucariccitelli.scheduledrestcaller.core.dto.ErrorResponse;

/**
 * @author luc created on 15 ago 2019
 *
 */
public class ValidationException extends BaseException {

	private static final long serialVersionUID = 2640635985970790810L;

	protected static final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public ValidationException() {
		super(new ErrorResponse("Validation error", httpStatus));
		setResponseStatus(httpStatus);
	}

	public ValidationException(ErrorResponse errorResponse) {
		super(errorResponse);
		setResponseStatus(httpStatus);
	}

}
