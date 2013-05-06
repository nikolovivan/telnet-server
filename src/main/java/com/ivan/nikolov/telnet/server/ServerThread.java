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

/**
 * Represents a thread that communicates with a single client.
 * 
 * @author Ivan Nikolov
 * 
 */
public class ServerThread extends Thread {

	private final Logger logger = LoggerFactory.getLogger(ServerThread.class);
	private final Socket socket;

	/**
	 * Creates a server thread with the given socket.
	 * 
	 * @param socket
	 */
	public ServerThread(final Socket socket) {
		this.socket = socket;
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
					out.println("Server: " + line);
					if (line.equalsIgnoreCase("quit")) {
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
		out.println("================================================================================");
		out.println("Hello, client! Start typing messages and I will repeat them. Use 'quit' to exit.");
		out.println("================================================================================");
	}
}
