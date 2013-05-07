/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

/**
 * All the supported commands.
 * 
 * @author Ivan Nikolov
 * 
 */
public enum Commands {
	PWD("pwd"),
	LS("ls"),
	CD("cd"),
	MKDIR("mkdir");

	private String name;

	/**
	 * Creates a command object.
	 * 
	 * @param name
	 *            The name of the command.
	 */
	Commands(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.getName();
	}
}
