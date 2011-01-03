package com.armchairfun.poker.common;

import com.armchairfun.poker.common.Table;
import com.armchairfun.poker.common.User;

import junit.framework.TestCase;

public class TestTable extends TestCase {

	private static final int MAX_TABLE_SIZE = 4;

	public void testAddPlayersToTable() {
		Table table = new Table();
		table.setTableSize(MAX_TABLE_SIZE);
		assertTrue(table.getPlayers().size() == 0);
		try {
			table.addPlayerToTable(new User());
			assertTrue(table.getPlayers().size() == 1);
		} catch (Exception e) {
			fail("should have been able to add player to table");
		}

	}

	public void testAddPlayersToFullTable() {
		Table table = new Table();
		table.setTableSize(1);
		assertTrue(table.getPlayers().size() == 0);
		assertFalse(table.isFull());
		try {
		table.addPlayerToTable(new User());
		table.addPlayerToTable(new User());
		} catch (Exception e) {
			assertTrue(table.isFull());
			return;
		}
		fail ("should have thrown exception when adding player to already full table");
		
		
	}
}
