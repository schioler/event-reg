package dk.schioler.event.base.exception;

import dk.schioler.event.base.EventBaseException;

public class DAOOwnerIdMissingException extends EventBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8954349845393276047L;

	public DAOOwnerIdMissingException() {

	}

	public DAOOwnerIdMissingException(String message) {
		super(message);

	}

	public DAOOwnerIdMissingException(Throwable cause) {
		super(cause);

	}

	public DAOOwnerIdMissingException(String message, Throwable cause) {
		super(message, cause);

	}

	public DAOOwnerIdMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
