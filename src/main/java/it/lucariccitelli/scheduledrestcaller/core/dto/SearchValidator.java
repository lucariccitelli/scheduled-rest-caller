package it.lucariccitelli.scheduledrestcaller.core.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import it.lucariccitelli.scheduledrestcaller.core.exception.ValidationException;

/**
 * @author luc created on 15 ago 2019
 *
 */
@Component
public class SearchValidator {

	private ErrorResponse error;

	public void validate(SearchDto search) {
		error = new ErrorResponse("Validation error", HttpStatus.BAD_REQUEST);

		if (search == null) {
			error.addError("dto", "Dto cannot be null.");
		} else {
			checkFields(search);
		}

		if (error.getErrorSize() > 0)
			throw new ValidationException(error);
	}

	/**
	 * @param dto
	 */
	private void checkFields(SearchDto dto) {
		if (Strings.isNullOrEmpty(dto.getData()) && Strings.isNullOrEmpty(dto.getAnd()) && Strings.isNullOrEmpty(dto.getOr()))
			error.addError("search", "Please provide at least a data to search.");
	}

}
