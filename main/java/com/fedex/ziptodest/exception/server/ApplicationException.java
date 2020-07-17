package com.fedex.ziptodest.exception.server;

/*
 *  Application Exception class is the Customized Exception class for user defined exceptions
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String code;

	public String getCode() {
		return code;
	}

	/**
	 * @param message
	 */

	public ApplicationException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * @param exception
	 */

	public ApplicationException(Exception exception, String code) {
		super(exception);
		this.code = code;

	}

	/**
	 * @param message
	 * @param throwable
	 */
	public ApplicationException(String message, Throwable throwable, String code) {
		super(message, throwable);
		this.code = code;
	}

}
