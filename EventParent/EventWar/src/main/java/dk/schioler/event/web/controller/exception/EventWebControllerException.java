package dk.schioler.event.web.controller.exception;

import dk.schioler.event.web.EventWebException;

public class EventWebControllerException extends EventWebException {

	private static final long serialVersionUID = -8954349845393276047L;

	public EventWebControllerException() {

	}

	public EventWebControllerException(String message) {
		super(message);

	}

	public EventWebControllerException(Throwable cause) {
		super(cause);

	}

	public EventWebControllerException(String message, Throwable cause) {
		super(message, cause);

	}

	public EventWebControllerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
