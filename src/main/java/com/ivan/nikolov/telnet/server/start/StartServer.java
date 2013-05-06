/**
 * 
 */
package com.ivan.nikolov.telnet.server.start;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivan.nikolov.telnet.server.Server;
import com.ivan.nikolov.telnet.server.arguments.Argument;
import com.ivan.nikolov.telnet.server.exceptions.InvalidParametersException;

/**
 * @author Ivan Nikolov
 * 
 */
public class StartServer {

	private static final Logger logger = LoggerFactory.getLogger(StartServer.class);
	private final Map<Argument, Integer> startArguments;
	private int port;

	/**
	 * Creates a server using the given args. They must contain the port the
	 * server should be started on.
	 * 
	 * @param args
	 * @throws InvalidParametersException
	 */
	public StartServer(final String[] args) throws InvalidParametersException {
		this.startArguments = this.getKnownArguments(args);
		if (!this.startArguments.containsKey(Argument.HELP)) {
			this.validateArguments(args);
			this.port = this.extractPortNumber(args, this.getStartArguments());
		}
	}

	/**
	 * @return the startArguments
	 */
	public Map<Argument, Integer> getStartArguments() {
		return this.startArguments;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * Starts the server that listens for connections.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		// get and validate the the arguments.
		StartServer.logger.info("Validating arguments...");
		StartServer startServer = null;
		try {
			startServer = new StartServer(args);
			if (startServer.startArguments.containsKey(Argument.HELP)) {
				startServer.showUsage();
				System.exit(0);
			}
		} catch (InvalidParametersException e) {
			StartServer.logger.error("Error parsing the start server arguments: ", e);
			System.exit(-1);
		}

		// create the server listening on the given port.
		StartServer.logger.info("Creating a server to listen on the given port...");
		Server server = null;
		try {
			server = new Server(startServer.getPort());
		} catch (IOException e) {
			StartServer.logger.error("Could not create the server: ", e);
			System.exit(-1);
		}

		// start the server.
		StartServer.logger.info("Starting the server...");
		try {
			server.start();
			server.join();
		} catch (IOException e) {
			StartServer.logger.error("Problem with the server: ", e);
			System.exit(-1);
		}

	}

	/**
	 * Validates the arguments the server was started with.
	 * 
	 * @param args
	 *            The arguments array.
	 * @throws InvalidParametersException
	 */
	private void validateArguments(final String[] args) throws InvalidParametersException {
		if (!this.getStartArguments().containsKey(Argument.PORT) || this.getStartArguments().get(Argument.PORT) > args.length - 2) {
			throw new InvalidParametersException("You need to specify a port argument!");
		} else if (this.getStartArguments().size() * 2 != args.length) {
			throw new InvalidParametersException("Unexpected input!");
		}
	}

	/**
	 * Gets the known arguments from the input.
	 * 
	 * @param args
	 *            The input arguments.
	 * @return A map where the key is the argument and the value is the index of
	 *         the argument.
	 */
	private Map<Argument, Integer> getKnownArguments(final String[] args) {
		Map<Argument, Integer> result = new HashMap<Argument, Integer>();
		for (int i = 0; i < args.length; i++) {
			Argument argument = Argument.fromAlias(args[i]);
			if (argument != null) {
				result.put(argument, i);
			}
		}
		return result;
	}

	/**
	 * Gets the port number from the arguments.
	 * 
	 * @param args
	 * @param params
	 * 
	 * @return The number of the port or stops the execution if the port is not
	 *         a number.
	 * @throws InvalidParametersException
	 */
	private int extractPortNumber(final String[] args, final Map<Argument, Integer> params) throws InvalidParametersException {
		int result = 0;
		try {
			result = Integer.valueOf(args[params.get(Argument.PORT) + 1]);
			if (result <= 0) {
				throw new InvalidParametersException("The port number must be a positive number!");
			}
		} catch (NumberFormatException e) {
			throw new InvalidParametersException("Invalid number for a port...", e);
		}
		return result;
	}

	/**
	 * Shows the program usage.
	 */
	public void showUsage() {
		System.out.println();
		System.out.println("Usage:");
		System.out.println("java -jar <this-jar-file> -port <port_number>");
		System.out.println();
	}

}
