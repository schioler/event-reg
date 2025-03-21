package dk.schioler.event.web.common;

import dk.schioler.event.web.EventWebException;

public class EventInsufficientInputDataException extends EventWebException {


	private static final long serialVersionUID = -8954349845393276047L;

	public EventInsufficientInputDataException() {

	}

	public EventInsufficientInputDataException(String message) {
		super(message);

	}

	public EventInsufficientInputDataException(Throwable cause) {
		super(cause);

	}

	public EventInsufficientInputDataException(String message, Throwable cause) {
		super(message, cause);

	}

	public EventInsufficientInputDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
