/**
 * 
 */
package com.ivan.nikolov.telnet.server.exceptions;


/**
 * @author Ivan Nikolov
 * 
 */
public class InvalidParametersException extends Exception {

	private static final long serialVersionUID = -6669456192256109092L;

	public InvalidParametersException() {
		super();
	}

	public InvalidParametersException(final String message) {
		super(message);
	}

	public InvalidParametersException(final String message, final Throwable e) {
		super(message, e);
	}

}
