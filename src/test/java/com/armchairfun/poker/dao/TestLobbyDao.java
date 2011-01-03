package com.armchairfun.poker.dao;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.armchairfun.poker.common.TableList;
import com.armchairfun.poker.dao.LobbyDao;

public class TestLobbyDao extends TestCase {
	
	LobbyDao lobbyDao = new LobbyDao();

	public TestLobbyDao(String name) {
		super(name);
	}

	public static Test suite() {
		return new TestSuite(TestLobbyDao.class);
	}

	public void testGetTableList() throws Exception {
		TableList tableList = lobbyDao.getTableList();
		assertTrue(tableList != null);
		assertTrue(tableList.getTableList().size() == 2);
		System.out.println(tableList.toXML());
	}

}
