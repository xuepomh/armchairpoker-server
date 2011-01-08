package com.armchairfun.poker.dao;

import com.armchairfun.testutils.ArmchairFunTestCase;
import com.armchairfun.poker.common.TableList;

public class TestLobbyDao extends ArmchairFunTestCase {
	
	LobbyDao lobbyDao = new LobbyDao();

	public void testGetTableList() throws Exception {
		TableList tableList = lobbyDao.getTableList();
		assertTrue(tableList != null);
		assertTrue(tableList.getTableList().size() == 2);
		System.out.println(tableList.toXML());
	}

}
