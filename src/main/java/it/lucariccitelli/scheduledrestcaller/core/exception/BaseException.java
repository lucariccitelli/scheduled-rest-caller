package it.lucariccitelli.scheduledrestcaller.core.exception;

import org.springframework.http.HttpStatus;

import it.lucariccitelli.scheduledrestcaller.core.dto.ErrorResponse;

/**
 * @author luc created on 15 ago 2019
 *
 */
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1239491483986710097L;

	protected final ErrorResponse errorResponse;
	protected static final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public BaseException() {
		super();
		errorResponse = new ErrorResponse();
		setResponseStatus(httpStatus);
	}

	public BaseException(ErrorResponse errorResponse) {
		super();
		this.errorResponse = errorResponse;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	protected void setResponseStatus(HttpStatus status) {
		if (!status.equals(errorResponse.getStatus()))
			errorResponse.setStatus(status);
	}

}
