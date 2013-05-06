/**
 * 
 */
package com.ivan.nikolov.telnet.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Represents the server itself.
 * 
 * @author Ivan Nikolov
 * 
 */
public class Server {

	private final ServerSocket socket;
	private boolean isListening = true;

	/**
	 * Creates a server that will start listening on the given port.
	 * 
	 * @param port
	 *            The port to listen on.
	 * @throws IOException
	 *             whenever a socket cannot be created on the given port.
	 */
	public Server(final int port) throws IOException {
		this.socket = new ServerSocket(port);
	}

	/**
	 * Starts listening on the port and accepting connections to other threads.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		while (this.isListening) {
			new ServerThread(this.socket.accept()).start();
		}
	}

	/**
	 * Sets the listening value to the given param.
	 * 
	 * @param isListening
	 */
	public void setListening(final boolean isListening) {
		this.isListening = isListening;
	}

	/**
	 * Cleans up by closing the socket.
	 * 
	 * @throws IOException
	 */
	public void join() throws IOException {
		this.socket.close();
	}
}
