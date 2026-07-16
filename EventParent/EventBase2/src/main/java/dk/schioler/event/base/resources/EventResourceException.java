package dk.schioler.event.base.resources;

import dk.schioler.event.base.EventBaseException;

public class EventResourceException extends EventBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -718429805932814594L;

	public EventResourceException() {

	}

	public EventResourceException(String message) {
		super(message);
	}

	public EventResourceException(Throwable cause) {
		super(cause);
	}

	public EventResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventResourceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
