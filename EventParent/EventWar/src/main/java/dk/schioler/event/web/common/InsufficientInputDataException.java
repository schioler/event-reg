package dk.schioler.event.web.common;

import dk.schioler.event.web.EventWebException;

public class InsufficientInputDataException extends EventWebException {


	private static final long serialVersionUID = -8954349845393276047L;

	public InsufficientInputDataException() {

	}

	public InsufficientInputDataException(String message) {
		super(message);

	}

	public InsufficientInputDataException(Throwable cause) {
		super(cause);

	}

	public InsufficientInputDataException(String message, Throwable cause) {
		super(message, cause);

	}

	public InsufficientInputDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
