package it.lucariccitelli.scheduledrestcaller.core.dto;

/**
 * @author luc created on 15 ago 2019
 *
 */
public class BooleanDto {
	boolean result;

	public BooleanDto() {
		this.result = false;
	}

	public BooleanDto(boolean result) {
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
