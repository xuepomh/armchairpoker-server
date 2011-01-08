package com.armchairfun.poker.common;

import java.util.List;

import com.armchairfun.testutils.ArmchairFunTestCase;
import com.armchairfun.poker.dao.ServerErrorException;

public class TestTable extends ArmchairFunTestCase {

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

	public void testSeatsAfterAddPlayersToTable() {
		Table table = new Table();
		table.setTableSize(MAX_TABLE_SIZE);
		User player1 = new User("user1", "pw1");
		User player2 = new User("user2", "pw2");
		
		
		assertTrue(table.getPlayers().size() == 0);
		try {
			table.addPlayerToTable(player1);
			assertTrue(table.getPlayers().size() == 1);
			table.addPlayerToTable(player2);
			// check the seats are occupied by the right players.
			assertTrue(table.getSeats().get(0).getPlayer().equals(player1));
			assertTrue(table.getSeats().get(1).getPlayer().equals(player2));
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
	
	public void testGetAllEligiblePlayers() {
		Table table = new Table();
		table.setPlayers(null);
		List<User> eligiblePlayers = table.getAllEligiblePlayers(); 
		assertNull(eligiblePlayers);
	}
	
	public void testPutPlayerInNextEmptySeatWhenFull() {
		Table table = new Table(0);
		table.setTableSize(0);
		User u1 = new User("user1", "1");
		try {
			table.putPlayerInNextEmptySeat(u1);
			fail("Expected ServerErrorException when no free seats to put player");
		} catch (Exception e) {
			assertTrue(e instanceof ServerErrorException);
			ServerErrorException se = (ServerErrorException)e;
			assertEquals(ErrorIds.TABLE_FULL, se.getErrorId());
		}
		
	}
}
