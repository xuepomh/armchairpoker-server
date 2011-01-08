package com.armchairfun.poker.communication;


import com.armchairfun.testutils.ArmchairFunTestCase;
import com.armchairfun.poker.common.UserActions;

public class TestPlayerRequest extends ArmchairFunTestCase {


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
		assertTrue(response.getStatus().equals(ServerResponse.RESPONSE_SUCCESS));
	}

}
