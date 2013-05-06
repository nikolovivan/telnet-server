/**
 * 
 */
package com.ivan.nikolov.telnet.server.exceptions;

/**
 * Thrown when an unknown command is issued.
 * 
 * @author Ivan Nikolov
 * 
 */
public class UnknownCommandException extends Exception {

	private static final long serialVersionUID = -6879804082119418856L;

	public UnknownCommandException() {
		super();
	}

	public UnknownCommandException(final String message) {
		super(message);
	}

	public UnknownCommandException(final String message, final Throwable e) {
		super(message, e);
	}

}
