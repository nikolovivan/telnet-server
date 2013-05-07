/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.util.List;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;
import com.ivan.nikolov.telnet.server.exceptions.UnsuccessfulCommandException;

/**
 * Represents the quit command.
 * 
 * @author Ivan Nikolov
 * 
 */
public class QuitCommand extends AbstractCommand {

	/**
	 * Creates the command for closing the connection with the client.
	 * 
	 * @param command
	 *            The command.
	 * @throws InvalidParametersException
	 */
	public QuitCommand(final String command) throws InvalidParametersException {
		super(Commands.QUIT, command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ivan.nikolov.telnet.server.commands.AbstractCommand#usage()
	 */
	@Override
	protected String usage() {
		StringBuilder builder = new StringBuilder("# quit usage: quit");
		builder.append("\n# result: closing the connection with the server");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ivan.nikolov.telnet.server.commands.AbstractCommand#validateParams
	 * (java.util.List)
	 */
	@Override
	protected void validateParams(final List<String> parameters) throws InvalidParametersException {
		if (parameters.size() > 0) {
			throw new InvalidParametersException(String.format("%s\n%s", "# The quit command should not have any parameters!", this.usage()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ivan.nikolov.telnet.server.commands.AbstractCommand#execute(com.ivan
	 * .nikolov.telnet.server.context.Context)
	 */
	@Override
	public String execute(final Context context) throws UnsuccessfulCommandException {
		return "Good bye!";
	}

}
