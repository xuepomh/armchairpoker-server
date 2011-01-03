package com.armchairfun;

import junit.framework.TestCase;

public class TestLobbyServer extends TestCase{
	
	public void testConstructor() {
		int port = 8000;
		LobbyServer server = new LobbyServer(port);
		assertEquals(port, server.getPort());
		assertTrue(server.getClientCount() == 0);
	}


}
