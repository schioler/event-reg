package dk.schioler.event.web.controller.exception;

import dk.schioler.event.web.EventWebException;

public class EventWebInValidParameterValuesException extends EventWebException {

	private static final long serialVersionUID = -8954349845393276047L;

	public EventWebInValidParameterValuesException() {

	}

	public EventWebInValidParameterValuesException(String message) {
		super(message);

	}

	public EventWebInValidParameterValuesException(Throwable cause) {
		super(cause);

	}

	public EventWebInValidParameterValuesException(String message, Throwable cause) {
		super(message, cause);

	}

	public EventWebInValidParameterValuesException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
