package dk.schioler.event.base.security;

public class EventSecurityException extends RuntimeException {


	private static final long serialVersionUID = -8954349845393276047L;

	public EventSecurityException() {

	}

	public EventSecurityException(String message) {
		super(message);

	}

	public EventSecurityException(Throwable cause) {
		super(cause);

	}

	public EventSecurityException(String message, Throwable cause) {
		super(message, cause);

	}

	public EventSecurityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
