package com.armchairfun;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import junit.framework.TestCase;

public class TestTableServer extends TestCase {

	public void testConstructor() {
		int port = 8000;
		TableServer server = new TableServer(port);
		assertEquals(port, server.getPort());
		assertTrue(server.getClientCount() == 0);
		server.start();

		// test a connection
		try {
			Socket requestSocket = new Socket("localhost", 8000);
			ObjectOutputStream out = new ObjectOutputStream(
					requestSocket.getOutputStream());
			out.flush();
			server.writeToAll("hello client, this is the server");

		} catch (Exception e) {
			fail(e.toString());
		} finally {
			server.finalize();
		}
	}

}
