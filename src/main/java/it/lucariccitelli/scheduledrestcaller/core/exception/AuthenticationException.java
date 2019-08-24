package it.lucariccitelli.scheduledrestcaller.core.exception;

import org.springframework.http.HttpStatus;

import it.lucariccitelli.scheduledrestcaller.core.dto.ErrorResponse;

/**
 * @author luc created on 15 ago 2019
 *
 */
public class AuthenticationException extends BaseException {

	private static final long serialVersionUID = -8105944312275320636L;

	protected static final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

	public AuthenticationException() {
		super(new ErrorResponse("Authentication error", httpStatus));
		setResponseStatus(httpStatus);
	}

	public AuthenticationException(ErrorResponse errorResponse) {
		super(errorResponse);
		setResponseStatus(httpStatus);
	}

}
