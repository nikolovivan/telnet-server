/**
 * 
 */
package com.ivan.nikolov.telnet.server.context;

/**
 * Represents a server context with the current directory and other useful
 * stuff. Helps maintain user session.
 * 
 * @author Ivan Nikolov
 * 
 */
public class Context {

	private String path;

	/**
	 * Creates a default context with the path set to empty.
	 */
	public Context() {
		this.path = "";
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(final String path) {
		this.path = path;
	}

}
