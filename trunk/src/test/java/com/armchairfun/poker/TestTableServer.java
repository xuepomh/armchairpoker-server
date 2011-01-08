package com.armchairfun.poker;

import java.io.ObjectOutputStream;
import java.net.Socket;

import com.armchairfun.testutils.ArmchairFunTestCase;

public class TestTableServer extends ArmchairFunTestCase {

	public void testClientConnect() {
		int port = 8000;
		TableServer server = new TableServer(port);

		assertEquals(port, server.getPort());
		assertTrue(server.getClientCount() == 0);
		synchronized (server) {
			server.start();
		}

		// test a connection
		try {
			Socket requestSocket = new Socket("localhost", port);
			ObjectOutputStream out = new ObjectOutputStream(
					requestSocket.getOutputStream());

			server.writeToAll("hello client, this is the server");
			out.flush();
			server.finalize();

		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
