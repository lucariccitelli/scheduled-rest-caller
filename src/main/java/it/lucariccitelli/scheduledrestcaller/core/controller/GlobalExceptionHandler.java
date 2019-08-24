package it.lucariccitelli.scheduledrestcaller.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.lucariccitelli.scheduledrestcaller.core.dto.ErrorResponse;
import it.lucariccitelli.scheduledrestcaller.core.util.ExceptionUtils;

/**
 * @author luc created on 15 ago 2019
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex) {
		return ExceptionUtils.generateResponse(ex);
	}

}
