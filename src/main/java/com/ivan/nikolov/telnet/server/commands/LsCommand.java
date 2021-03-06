/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;
import com.ivan.nikolov.telnet.server.exceptions.UnsuccessfulCommandException;

/**
 * Represents the ls command.
 * 
 * @author Ivan Nikolov
 * 
 */
public class LsCommand extends AbstractCommand {

	/**
	 * Creates an ls command from the given user input.
	 * 
	 * @param command
	 *            The user input.
	 * @throws InvalidParametersException
	 */
	public LsCommand(final String command) throws InvalidParametersException {
		super(Commands.LS, command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ivan.nikolov.telnet.server.commands.AbstractCommand#usage()
	 */
	@Override
	protected String usage() {
		StringBuilder builder = new StringBuilder("# ls usage: ls [path]");
		builder.append(AbstractCommand.NEW_LINE);
		builder.append("# result: the files and folders in the current/given directory");
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
		if (parameters.size() > 1) {
			throw new InvalidParametersException(String.format("%s%s%s", "# Wrong usage of the ls command!", AbstractCommand.NEW_LINE, this.usage()));
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
		String workingFolder = this.parameters.size() > 0 ? this.parameters.get(0) : context.getPath();
		File dir = new File(workingFolder);
		if (!dir.exists()) {
			throw new UnsuccessfulCommandException("The given directory does not exist.");
		} else if (!dir.isDirectory()) {
			throw new UnsuccessfulCommandException("Not a directory!");
		}
		StringBuilder result = new StringBuilder();
		String[] contents = dir.list();
		// show them sorted
		Arrays.sort(contents);
		for (String name : contents) {
			result.append(name).append(AbstractCommand.NEW_LINE);
		}
		return result.toString();
	}

}
