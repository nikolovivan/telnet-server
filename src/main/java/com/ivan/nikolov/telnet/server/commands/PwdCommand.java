/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.util.List;

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
		super(CommandFactory.PWD, command);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ivan.nikolov.telnet.server.commands.AbstractCommand#usage()
	 */
	@Override
	public void usage() {
		System.out.println("pwd usage: pwd");
		System.out.println("result: the working directory");
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
			throw new InvalidParametersException("The pwd command should not have any parameters!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ivan.nikolov.telnet.server.commands.AbstractCommand#execute()
	 */
	@Override
	protected String execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
