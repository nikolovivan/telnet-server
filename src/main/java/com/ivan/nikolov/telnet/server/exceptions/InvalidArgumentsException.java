/**
 * 
 */
package com.ivan.nikolov.telnet.server.exceptions;

/**
 * An exception that is thrown when the server is started with invalid
 * arguments.
 * 
 * @author Ivan Nikolov
 * 
 */
public class InvalidArgumentsException extends Exception {

	private static final long serialVersionUID = -6669456192256109092L;

	public InvalidArgumentsException() {
		super();
	}

	public InvalidArgumentsException(final String message) {
		super(message);
	}

	public InvalidArgumentsException(final String message, final Throwable e) {
		super(message, e);
	}

}
