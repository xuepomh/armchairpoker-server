package com.armchairfun.communication;

import com.armchairfun.common.UserActions;
import com.armchairfun.communication.PlayerRequest;
import com.armchairfun.communication.ServerResponse;
import com.armchairfun.communication.ServerResponseStatus;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestPlayerRequest extends TestCase {

	public TestPlayerRequest(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(TestPlayerRequest.class);
	}

	/** Test something. **/
	public void testCreateFromXML() throws Exception {
		String joinTableRequestXml = "<request userId=\"cjohn\""
				+ " action=\"JOIN_TABLE\" value=\"\"/>";
		PlayerRequest joinTableRequest = new PlayerRequest(joinTableRequestXml);
		assertEquals("cjohn", joinTableRequest.getUserId());
		assertEquals(UserActions.JOIN_TABLE, joinTableRequest.getAction());

		String betRequestXml = "<request userId=\"cjohn\""
				+ " action=\"BET\" value=\"24\"/>";
		PlayerRequest betRequest = new PlayerRequest(betRequestXml);
		assertEquals("cjohn", betRequest.getUserId());
		assertEquals(UserActions.BET, betRequest.getAction());
		assertEquals("24", betRequest.getValue());

	}

	public void testLoginAttempt() throws Exception {
		String loginRequestXml = "<request userId=\"cjohn\""
				+ " action=\"CONNECT\" value=\"password\"/>";
		PlayerRequest loginRequest = new PlayerRequest(loginRequestXml);
		assertEquals("cjohn", loginRequest.getUserId());
		assertEquals(UserActions.CONNECT, loginRequest.getAction());
		ServerResponse response = loginRequest.handleRequest();
		assertTrue(response.getStatus().equals(ServerResponseStatus.SUCCESS));
	}

}
