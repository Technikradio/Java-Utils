package org.technikradio.universal_tools;

public class DoubleCommandException extends Exception {

	private static final long serialVersionUID = -4861435731768684416L;

	public DoubleCommandException() {
		super();
	}

	public DoubleCommandException(String message) {
		super(message);
	}

	public DoubleCommandException(Throwable cause) {
		super(cause);
	}

	public DoubleCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoubleCommandException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
