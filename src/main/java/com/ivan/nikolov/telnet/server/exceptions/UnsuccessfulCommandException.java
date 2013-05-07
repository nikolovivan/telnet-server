/**
 * 
 */
package com.ivan.nikolov.telnet.server.exceptions;

/**
 * @author Ivan Nikolov
 * 
 */
public class UnsuccessfulCommandException extends Exception {

	private static final long serialVersionUID = -4551127589145247591L;

	public UnsuccessfulCommandException() {
		super();
	}

	public UnsuccessfulCommandException(final String message) {
		super(message);
	}

	public UnsuccessfulCommandException(final String message, final Throwable e) {
		super(message, e);
	}
}
