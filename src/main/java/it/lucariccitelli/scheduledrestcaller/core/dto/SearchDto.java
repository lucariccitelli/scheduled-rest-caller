package it.lucariccitelli.scheduledrestcaller.core.dto;

/**
 * @author luc created on 16 ago 2019
 *
 */
public class SearchDto {
	String data;
	String and;
	String or;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAnd() {
		return and;
	}

	public void setAnd(String and) {
		this.and = and;
	}

	public String getOr() {
		return or;
	}

	public void setOr(String or) {
		this.or = or;
	}

}
