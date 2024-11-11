package dk.schioler.event.web.controller;

import dk.schioler.event.web.EventWebException;

public class EventWebInsufficientParameterValuesException extends EventWebException {

	private static final long serialVersionUID = -8954349845393276047L;

	public EventWebInsufficientParameterValuesException() {

	}

	public EventWebInsufficientParameterValuesException(String message) {
		super(message);

	}

	public EventWebInsufficientParameterValuesException(Throwable cause) {
		super(cause);

	}

	public EventWebInsufficientParameterValuesException(String message, Throwable cause) {
		super(message, cause);

	}

	public EventWebInsufficientParameterValuesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
