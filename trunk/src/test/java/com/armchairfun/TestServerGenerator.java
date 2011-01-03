package com.armchairfun;

import junit.framework.TestCase;

public class TestServerGenerator extends TestCase{

	ServerGenerator generator = new ServerGenerator();
	
	public void setUp() {
		generator = new ServerGenerator();
	}
	
	public void tearDown() {
		generator.stopAllServers();
	}
	
	public void testBuildPolicyServer() {
		generator.buildPolicyServer();
		assertTrue(generator.getPolicyServer() != null);
		assertTrue(generator.getPolicyServer().isAlive());
	}
	
	public void testBuildLobbyServer() {
		generator.buildLobbyServer();
		assertTrue(generator.getLobbyServer() != null);
		assertTrue(generator.getLobbyServer().isAlive());
	}
	
	public void testBuildTableServers() {
		generator.buildTableServers();
		assertTrue(generator.getTableServers() != null);
		assertTrue(generator.getTableServers().size() == 2);
	}
	
	public void testBuildAllServers() {
		generator.buildAllServers();
		assertTrue(generator.getPolicyServer() != null);
		assertTrue(generator.getLobbyServer() != null);
		assertTrue(generator.getTableServers() != null);
		assertTrue(generator.getTableServers().size() == 2);
	}
	
}
