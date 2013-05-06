/**
 * 
 */
package com.ivan.nikolov.telnet.server.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;

/**
 * Abstract class for the commands that we will supports.
 * 
 * @author Ivan Nikolov
 * 
 */
public abstract class AbstractCommand {

	protected final Commands command;
	protected List<String> parameters = new ArrayList<String>();

	/**
	 * Creates a command with its name and number of parameters.
	 * 
	 * @param command
	 *            The command object.
	 * @param commandString
	 *            The command string that the user entered.
	 * @throws InvalidParametersException
	 */
	public AbstractCommand(final Commands command, final String commandString) throws InvalidParametersException {
		this.command = command;
		this.parse(commandString);
		this.validateParams(this.parameters);
	}

	/**
	 * @return the command name.
	 */
	public String getCommandName() {
		return this.command.getName();
	}

	/**
	 * @return the command parameters.
	 */
	public List<String> getParameters() {
		return this.parameters;
	}

	/**
	 * Parses the command that was issued.
	 * 
	 * @param command
	 *            The issued command.
	 */
	protected void parse(final String command) {
		StringTokenizer tokenizer = new StringTokenizer(command);
		// skip the command name.
		if (tokenizer.hasMoreTokens()) {
			tokenizer.nextToken();
		}
		while (tokenizer.hasMoreTokens()) {
			this.parameters.add(tokenizer.nextToken());
		}
	}

	/**
	 * Gets info about how the command should be used.
	 */
	protected abstract String usage();

	/**
	 * Validates the command parameters.
	 * 
	 * @param The
	 *            parameters of the command after parsing it.
	 */
	protected abstract void validateParams(List<String> parameters) throws InvalidParametersException;

	/**
	 * Executes the command and returns the result as a string that can be
	 * displayed.
	 * 
	 * @param context
	 *            The current context of the server.
	 */
	public abstract String execute(Context context);
}
