package dk.schioler.event.base.entity;

public class BaseEventException extends RuntimeException {

	private static final long serialVersionUID = -8954349845393276047L;

	public BaseEventException() {

	}

	public BaseEventException(java.lang.String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public BaseEventException(java.lang.String message, Throwable cause) {
		super(message, cause);

	}

	public BaseEventException(java.lang.String message) {
		super(message);

	}

	public BaseEventException(Throwable cause) {
		super(cause);

	}

//	public EventBaseException(String message) {
//		super(message);
//
//	}
//
//	public EventBaseException(Throwable cause) {
//		super(cause);
//
//	}
//
//	public EventBaseException(String message, Throwable cause) {
//		super(message, cause);
//
//	}


}
