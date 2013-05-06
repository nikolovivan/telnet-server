/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.util.List;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;

/**
 * Represents the pwd command.
 * 
 * @author Ivan Nikolov
 * 
 */
public class PwdCommand extends AbstractCommand {

	/**
	 * Creates a pwd command from the given user input command.
	 * 
	 * @param command
	 *            The user input.
	 * @throws InvalidParametersException
	 */
	public PwdCommand(final String command) throws InvalidParametersException {
		super(Commands.PWD, command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ivan.nikolov.telnet.server.commands.AbstractCommand#usage()
	 */
	@Override
	protected String usage() {
		StringBuilder builder = new StringBuilder("# pwd usage: pwd");
		builder.append("\n# result: the working directory");
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
			throw new InvalidParametersException(String.format("%s\n%s", "# The pwd command should not have any parameters!", this.usage()));
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
	public String execute(final Context context) {
		// as simple as that.
		return context.getPath();
	}

}
