/**
 * 
 */
package com.ivan.nikolov.telnet.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.ivan.nikolov.telnet.server.commands.AbstractCommand;
import com.ivan.nikolov.telnet.server.commands.CommandFactory;
import com.ivan.nikolov.telnet.server.commands.QuitCommand;
import com.ivan.nikolov.telnet.server.context.Context;
import com.ivan.nikolov.telnet.server.exceptions.UnknownCommandException;

/**
 * Represents a thread that communicates with a single client.
 * 
 * @author Ivan Nikolov
 * 
 */
public class ServerThread extends Thread {

	private final Logger logger = LoggerFactory.getLogger(ServerThread.class);
	private final Socket socket;
	private final Context context;

	/**
	 * Creates a server thread with the given socket.
	 * 
	 * @param socket
	 */
	public ServerThread(final Socket socket) {
		this.socket = socket;
		this.context = new Context();
		this.initialize();
	}

	/**
	 * Initializes the thread with a context, etc.
	 */
	private void initialize() {
		this.context.setPath(System.getProperty("user.dir"));
	}

	/**
	 * Runs the thread.
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			this.doProcessing();
		} catch (IOException e) {
			this.logger.error("Problem while processing a thread: ", e);
		}
	}

	/**
	 * Does the processing that the server thread has to do.
	 * 
	 * @throws IOException
	 */
	private void doProcessing() throws IOException {
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(this.socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

			this.printGreetingMessage(out);

			String line;
			while ((line = in.readLine()) != null) {
				if (!Strings.isNullOrEmpty(line)) {
					String commandResult = null;
					AbstractCommand command = null;
					try {
						command = CommandFactory.getCommand(line);
						commandResult = command.execute(this.context);
					} catch (UnknownCommandException e) {
						commandResult = "Unknown command...";
					} catch (Exception e) {
						// handles invalid parameters and unsuccessful command
						commandResult = e.getMessage();
					}

					out.println(commandResult);
					if (command instanceof QuitCommand) {
						break;
					}
				}
			}

		} catch (IOException e) {
			this.logger.error("Error running a server thread: ", e);
		} finally {
			// cleanup
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			this.socket.close();
		}
	}

	/**
	 * Prints a greeting message to the client.
	 * 
	 * @param out
	 *            The writer to the socket output stream.
	 */
	private void printGreetingMessage(final PrintWriter out) {
		out.println("====================================================================");
		out.println("A simple server that executes the following commands:");
		out.println("* pwd - shows the current working directory.");
		out.println("* ls [folder path] - lists the contents of the current/given folder.");
		out.println("* cd <folder> - navigates to a folder.");
		out.println("* mkdir <folder name> - creates a folder.");
		out.println("* quit - exits the program.");
		out.println("====================================================================");
	}
}
