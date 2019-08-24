package it.lucariccitelli.scheduledrestcaller.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author luc created on 15 ago 2019
 *
 */
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1055028097938032298L;

	private String globalMessage;
	@JsonInclude(value = Include.NON_NULL)
	private Map<String, List<String>> errorMessages;
	private HttpStatus status;

	public ErrorResponse() {
		this.globalMessage = "Generic Error";
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ErrorResponse(String errorMsg) {
		this.globalMessage = errorMsg;
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ErrorResponse(String errorMsg, HttpStatus status) {
		this.globalMessage = errorMsg;
		this.status = status;
	}

	public String getGlobalMessage() {
		return globalMessage;
	}

	public void setGlobalMessage(String globalMessage) {
		this.globalMessage = globalMessage;
	}

	public Map<String, List<String>> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, List<String>> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * Adds an error message to errors map
	 * 
	 * @param group
	 * @param msg
	 */
	public void addError(String group, String... msg) {
		List<String> errorMsg = new ArrayList<>(Arrays.asList(msg));

		if (errorMessages == null)
			errorMessages = new HashMap<>();

		if (errorMessages.containsKey(group))
			errorMessages.get(group).addAll(errorMsg);
		else
			errorMessages.put(group, errorMsg);
	}

	/**
	 * Returns total error message count
	 * 
	 * @return
	 */
	public int getErrorSize() {
		if (errorMessages == null)
			return 0;
		return errorMessages.size();
	}

}
