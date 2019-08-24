package it.lucariccitelli.scheduledrestcaller.core.util;

/**
 * @author luc created on 16 ago 2019
 *
 */
public class ScheduledRestCallerUtils {
	private ScheduledRestCallerUtils() {
		throw new IllegalStateException("Utils class");
	}

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm";

	public static final String ID_NOT_NULL = "The given id cannot be null";
	public static final String UNAUTHORIZED_ACCESS = "Unauthorized access detected.";
}
