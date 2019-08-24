package it.lucariccitelli.scheduledrestcaller.core.exception;

import org.springframework.http.HttpStatus;

import it.lucariccitelli.scheduledrestcaller.core.dto.ErrorResponse;

/**
 * @author luc created on 15 ago 2019
 *
 */
public class NotFoundException extends BaseException {

	private static final long serialVersionUID = -141075004139177530L;

	protected static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public NotFoundException() {
		super(new ErrorResponse("Not Found error", httpStatus));
		setResponseStatus(httpStatus);
	}

	public NotFoundException(String msg) {
		super(new ErrorResponse(msg, httpStatus));
	}

	public NotFoundException(String entity, Long id) {
		super(new ErrorResponse(String.format("Cannot find %s with id [%s]", entity, id), httpStatus));
	}

	public NotFoundException(ErrorResponse errorResponse) {
		super(errorResponse);
		setResponseStatus(httpStatus);
	}

}
