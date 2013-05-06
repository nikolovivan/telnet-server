/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import com.google.common.base.Strings;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;
import com.ivan.nikolov.telnet.server.exceptions.UnknownCommandException;

/**
 * A factory class that creates a command object from the given user input.
 * 
 * @author Ivan Nikolov
 * 
 */
public class CommandFactory {

	public static final String PWD = "pwd";

	/**
	 * Gets a command from the user input.
	 * 
	 * @param command
	 *            The command the user issued.
	 * @return
	 * @throws InvalidParametersException
	 * @throws UnknownCommandException
	 */
	public static AbstractCommand getCommand(final String command) throws InvalidParametersException, UnknownCommandException {
		if (Strings.isNullOrEmpty(command)) {
			throw new IllegalArgumentException("command");
		}
		AbstractCommand result = null;
		String commandName = CommandFactory.getCommandName(command);
		if (commandName.equalsIgnoreCase(CommandFactory.PWD)) {
			result = new PwdCommand(command);
		} else {
			throw new UnknownCommandException(command);
		}
		return result;
	}

	/**
	 * Gets the name of the command.
	 * 
	 * @param command
	 *            The user command.
	 * @return
	 */
	private static String getCommandName(final String command) {
		int firstSpace = command.indexOf(' ');
		return firstSpace == -1 ? command : command.substring(0, firstSpace);
	}
}
