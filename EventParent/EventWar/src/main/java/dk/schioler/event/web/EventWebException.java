package dk.schioler.event.web;

public class EventWebException extends RuntimeException {


	private static final long serialVersionUID = -8954349845393276047L;

	public EventWebException() {

	}

	public EventWebException(String message) {
		super(message);

	}

	public EventWebException(Throwable cause) {
		super(cause);

	}

	public EventWebException(String message, Throwable cause) {
		super(message, cause);

	}

	public EventWebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
