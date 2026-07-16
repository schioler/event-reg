package dk.schioler.event.base.organizeable;

public class OrganizeableException extends RuntimeException {

	private static final long serialVersionUID = -8954349845393276047L;

	public OrganizeableException() {

	}

	public OrganizeableException(java.lang.String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public OrganizeableException(java.lang.String message, Throwable cause) {
		super(message, cause);

	}

	public OrganizeableException(java.lang.String message) {
		super(message);

	}

	public OrganizeableException(Throwable cause) {
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
