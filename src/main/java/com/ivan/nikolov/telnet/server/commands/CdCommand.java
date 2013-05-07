/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;

/**
 * Represents the cd command...
 * 
 * @author Ivan Nikolov
 * 
 */
public class CdCommand extends AbstractCommand {

	/**
	 * Creates a cd command from the user input.
	 * 
	 * @param command
	 *            The command the user entered.
	 * @throws InvalidParametersException
	 */
	public CdCommand(final String command) throws InvalidParametersException {
		super(Commands.CD, command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ivan.nikolov.telnet.server.commands.AbstractCommand#usage()
	 */
	@Override
	protected String usage() {
		StringBuilder builder = new StringBuilder("# cd usage: cd <path>");
		builder.append("\n# result: changes the current context to the given directory (either relative or absolute)");
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
		if (parameters.size() != 1) {
			throw new InvalidParametersException(String.format("%s\n%s", "# Wrong usage of the cd command!", this.usage()));
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
		String workingFolder = context.getPath();
		String path = this.parameters.get(0);

		File dir = new File(path);
		// then we have to make it absolute.
		if (!dir.isAbsolute()) {
			path = workingFolder + File.separatorChar + path;
			dir = new File(path);
		}

		if (!dir.exists()) {
			return "The given directory does not exist.";
		} else if (!dir.isDirectory()) {
			return "Not a directory!";
		} else {
			try {
				path = dir.getCanonicalPath();
				context.setPath(path);
				return path;
			} catch (IOException e) {
				return "Error getting canonical path. Directory not changed.";
			}
		}
	}

}
