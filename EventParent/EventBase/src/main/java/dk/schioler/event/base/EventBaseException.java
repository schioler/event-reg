package dk.schioler.event.base;

import org.apache.xpath.operations.String;

public class EventBaseException extends RuntimeException {

	private static final long serialVersionUID = -8954349845393276047L;

	public EventBaseException() {

	}

	public EventBaseException(String message) {
		super(message);

	}

	public EventBaseException(Throwable cause) {
		super(cause);

	}

	public EventBaseException(String message, Throwable cause) {
		super(message, cause);

	}

	public EventBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
