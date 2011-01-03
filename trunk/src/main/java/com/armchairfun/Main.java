package com.armchairfun;

/**
 * Main is used to start the servers and handle the debug messages.
 * 
 */
public class Main {

	/**
	 * Starts the chat server and the policy server
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		ServerGenerator generator = new ServerGenerator();
		generator.buildAllServers();

	}

}
