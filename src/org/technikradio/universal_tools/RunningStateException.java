package org.technikradio.universal_tools;

public class RunningStateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -620367682206449241L;

	public RunningStateException() {
		super();
	}

	public RunningStateException(String message) {
		super(message);
	}

	public RunningStateException(Throwable cause) {
		super(cause);
	}

	public RunningStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public RunningStateException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
