package com.armchairfun.poker;

import java.io.ObjectOutputStream;
import java.net.Socket;

import com.armchairfun.poker.PolicyServer;

import junit.framework.TestCase;

public class TestPolicyServer extends TestCase {

	public void testClientConnect() {
		int port = 8080;
		PolicyServer server = new PolicyServer(port);
		assertEquals(port, server.getPort());
		server.start();

		// test a connection
		try {
			Socket requestSocket = new Socket("localhost", port);
			ObjectOutputStream out = new ObjectOutputStream(
					requestSocket.getOutputStream());
			out.flush();
			server.finalize();

		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
