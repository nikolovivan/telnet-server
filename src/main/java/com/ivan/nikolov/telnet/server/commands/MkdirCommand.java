/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;
import com.ivan.nikolov.telnet.server.exceptions.UnsuccessfulCommandException;

/**
 * Represents the mkdir command.
 * 
 * @author Ivan Nikolov
 * 
 */
public class MkdirCommand extends AbstractCommand {

	/**
	 * Creates a mkdir command from the user input.
	 * 
	 * @param command
	 *            The command the user issued.
	 * @throws InvalidParametersException
	 */
	public MkdirCommand(final String command) throws InvalidParametersException {
		super(Commands.MKDIR, command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ivan.nikolov.telnet.server.commands.AbstractCommand#usage()
	 */
	@Override
	protected String usage() {
		StringBuilder builder = new StringBuilder("# mkdir usage: mkdir <path>");
		builder.append(AbstractCommand.NEW_LINE);
		builder.append("# result: creates the directory in the <path> if it does not exist and if possible");
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
			throw new InvalidParametersException(String.format("%s%s%s", "# Wrong usage of the mkdir command!", AbstractCommand.NEW_LINE, this.usage()));
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
		String workingFolder = context.getPath();
		String path = this.parameters.get(0);

		File dir = new File(path);
		// then we have to make it absolute.
		if (!dir.isAbsolute()) {
			path = workingFolder + File.separatorChar + path;
			dir = new File(path);
		}

		if (dir.exists()) {
			throw new UnsuccessfulCommandException("The given directory already exists.");
		} else {
			try {
				path = dir.getCanonicalPath();
				if (dir.mkdirs()) {
					return "Created!";
				}
				throw new UnsuccessfulCommandException("Could not create directory!");
			} catch (IOException e) {
				throw new UnsuccessfulCommandException("Error getting canonical path. Directory not changed.", e);
			} catch (SecurityException e) {
				throw new UnsuccessfulCommandException("You do not have permissions to create the given directory.", e);
			}
		}
	}

}
